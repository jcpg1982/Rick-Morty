package pe.com.master.machines.domain.repositoryImpl.combine

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.common.response.toErrorType
import pe.com.master.machines.data.repository.database.StoryCharacterLocalDataRepository
import pe.com.master.machines.data.repository.remote.StoryCharacterRemoteDataRepository
import pe.com.master.machines.domain.repository.combine.GetSingleCharacterCombineUseCase
import pe.com.master.machines.model.model.StoryCharacter
import javax.inject.Inject

class GetSingleCharacterCombineUseCaseImpl @Inject constructor(
    private val storyCharacterLocalDataRepository: StoryCharacterLocalDataRepository,
    private val storyCharacterRemoteDataRepository: StoryCharacterRemoteDataRepository,
) : GetSingleCharacterCombineUseCase {

    override fun invoke(isHaveInternet: Boolean, id: Int): Flow<Resource<StoryCharacter>> {
        val localFlow = storyCharacterLocalDataRepository.getSingleCharacter(id)
        return if (!isHaveInternet) {
            localFlow.map { local ->
                when (local) {
                    is Resource.Success -> Resource.Success(local.data)
                    is Resource.Error -> Resource.Error(local.error)
                }
            }
        } else {
            val remoteFlow = storyCharacterRemoteDataRepository.getLoadSingleCharacter(id)
            combine(localFlow, remoteFlow) { local, remote ->
                when {
                    remote is Resource.Success -> {
                        storyCharacterLocalDataRepository.saveAllCharacters(listOf(remote.data))
                            .collect {}
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