package pe.com.master.machines.domain.repositoryImpl.combine

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.common.response.toErrorType
import pe.com.master.machines.data.repository.database.StoryCharacterLocalDataRepository
import pe.com.master.machines.data.repository.preferences.PreferencesDataRepository
import pe.com.master.machines.data.repository.remote.StoryCharacterRemoteDataRepository
import pe.com.master.machines.domain.repository.combine.GetSingleCharacterByNameCombineUseCase
import pe.com.master.machines.model.model.Info
import pe.com.master.machines.model.response.ResponseAllCharacter
import javax.inject.Inject

class GetSingleCharacterByNameCombineUseCaseImpl @Inject constructor(
    private val storyCharacterLocalDataRepository: StoryCharacterLocalDataRepository,
    private val storyCharacterRemoteDataRepository: StoryCharacterRemoteDataRepository,
    private val preferencesDataRepository: PreferencesDataRepository,
) : GetSingleCharacterByNameCombineUseCase {

    override fun invoke(
        isHaveInternet: Boolean, page: Int, name: String, status: String
    ): Flow<Resource<ResponseAllCharacter>> {
        val offset = (page - 1) * PAGE_SIZE
        val localFlow =
            storyCharacterLocalDataRepository.searchCharacterByName(PAGE_SIZE, offset, name, status)
        return if (!isHaveInternet) {
            localFlow.map { local ->
                val totalPages = preferencesDataRepository.totalPages.firstOrNull() ?: -1
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
            val remoteFlow =
                storyCharacterRemoteDataRepository.searchCharacterByName(page, name, status)
            combine(localFlow, remoteFlow) { local, remote ->
                val totalPages = preferencesDataRepository.totalPages.firstOrNull() ?: -1
                when {
                    remote is Resource.Success -> {
                        storyCharacterLocalDataRepository.saveAllCharacters(remote.data.results)
                            .collect {}
                        preferencesDataRepository.setTotalPages(remote.data.info.pages)
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