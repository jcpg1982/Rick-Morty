package pe.com.master.machines.domain.repositoryImpl.remote

import pe.com.master.machines.data.repository.remote.StoryCharacterRemoteDataRepository
import pe.com.master.machines.domain.repository.remote.GetSingleCharacterRemoteUseCase
import javax.inject.Inject

class GetSingleCharacterRemoteUseCaseImpl @Inject constructor(
    private val storyCharacterRemoteDataRepository: StoryCharacterRemoteDataRepository
) : GetSingleCharacterRemoteUseCase {

    override fun invoke(id: Int) = storyCharacterRemoteDataRepository.getLoadSingleCharacter(id)

}