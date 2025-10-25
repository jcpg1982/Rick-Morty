package pe.com.master.machines.domain.repositoryImpl.combine

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.common.response.toErrorType
import pe.com.master.machines.domain.repository.combine.GetAllCharactersCombineUseCase
import pe.com.master.machines.domain.repository.database.GetAllCharactersLocalUseCase
import pe.com.master.machines.domain.repository.database.SaveAllCharactersLocalUseCase
import pe.com.master.machines.domain.repository.preferences.TotalPagesUsesCase
import pe.com.master.machines.domain.repository.remote.GetAllCharactersRemoteUseCase
import pe.com.master.machines.model.model.Info
import pe.com.master.machines.model.response.ResponseAllCharacter
import javax.inject.Inject

class GetAllCharactersCombineUseCaseImpl @Inject constructor(
    private val getAllCharactersLocalUseCase: GetAllCharactersLocalUseCase,
    private val getAllCharactersRemoteUseCase: GetAllCharactersRemoteUseCase,
    private val saveAllCharactersLocalUseCase: SaveAllCharactersLocalUseCase,
    private val totalPagesUsesCase: TotalPagesUsesCase,
) : GetAllCharactersCombineUseCase {

    override fun invoke(
        isHaveInternet: Boolean, page: Int
    ): Flow<Resource<ResponseAllCharacter>> {
        val offset = (page - 1) * PAGE_SIZE
        val localFlow = getAllCharactersLocalUseCase(PAGE_SIZE, offset)
        return if (!isHaveInternet) {
            localFlow.map { local ->
                val totalPages = totalPagesUsesCase.invoke().firstOrNull() ?: -1
                when (local) {
                    is Resource.Success -> {
                        if (local.data.isEmpty()) {
                            Resource.Error(Throwable("No tiene internet y no hay datos almacenados").toErrorType())
                        } else {
                            Resource.Success(
                                ResponseAllCharacter(
                                    Info(pages = totalPages),
                                    local.data
                                )
                            )
                        }
                    }

                    is Resource.Error -> Resource.Error(local.error)
                }
            }
        } else {
            val remoteFlow = getAllCharactersRemoteUseCase(page)
            combine(localFlow, remoteFlow) { local, remote ->
                val totalPages = totalPagesUsesCase.invoke().firstOrNull() ?: -1
                when {
                    remote is Resource.Success -> {
                        saveAllCharactersLocalUseCase(remote.data.results).collect {}
                        totalPagesUsesCase(remote.data.info.pages)
                        Resource.Success(remote.data)
                    }

                    remote is Resource.Error && local is Resource.Success && local.data.isNotEmpty() -> {
                        Resource.Success(ResponseAllCharacter(Info(pages = totalPages), local.data))
                    }

                    local is Resource.Success && local.data.isNotEmpty() -> {
                        Resource.Success(ResponseAllCharacter(Info(pages = totalPages), local.data))
                    }

                    remote is Resource.Error -> Resource.Error(remote.error)
                    local is Resource.Error -> Resource.Error(local.error)
                    else -> Resource.Error(Throwable().toErrorType())
                }
            }.distinctUntilChanged()
        }
    }
}
