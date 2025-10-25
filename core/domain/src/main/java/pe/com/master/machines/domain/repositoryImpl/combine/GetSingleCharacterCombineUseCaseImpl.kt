package pe.com.master.machines.domain.repositoryImpl.combine

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.common.response.toErrorType
import pe.com.master.machines.domain.repository.combine.GetSingleCharacterCombineUseCase
import pe.com.master.machines.domain.repository.database.GetSingleCharacterLocalUseCase
import pe.com.master.machines.domain.repository.database.SaveAllCharactersLocalUseCase
import pe.com.master.machines.domain.repository.remote.GetSingleCharacterRemoteUseCase
import pe.com.master.machines.model.model.StoryCharacter
import javax.inject.Inject

class GetSingleCharacterCombineUseCaseImpl @Inject constructor(
    private val getSingleCharacterLocalUseCase: GetSingleCharacterLocalUseCase,
    private val getSingleCharacterRemoteUseCase: GetSingleCharacterRemoteUseCase,
    private val saveAllCharactersLocalUseCase: SaveAllCharactersLocalUseCase,
) : GetSingleCharacterCombineUseCase {

    override fun invoke(isHaveInternet: Boolean, id: Int): Flow<Resource<StoryCharacter>> {
        val localFlow = getSingleCharacterLocalUseCase.invoke(id)
        return if (!isHaveInternet) {
            localFlow.map { local ->
                when (local) {
                    is Resource.Success -> Resource.Success(local.data)
                    is Resource.Error -> Resource.Error(local.error)
                }
            }
        } else {
            val remoteFlow = getSingleCharacterRemoteUseCase.invoke(id)
            combine(localFlow, remoteFlow) { local, remote ->
                when {
                    remote is Resource.Success -> {
                        saveAllCharactersLocalUseCase.invoke(listOf(remote.data)).collect {}
                        Resource.Success(remote.data)
                    }

                    remote is Resource.Error && local is Resource.Success -> {
                        Resource.Success(local.data)
                    }

                    local is Resource.Success -> {
                        Resource.Success(local.data)
                    }

                    remote is Resource.Error -> Resource.Error(remote.error)
                    local is Resource.Error -> Resource.Error(local.error)
                    else -> Resource.Error(Throwable().toErrorType())
                }
            }.distinctUntilChanged()
        }
    }
}