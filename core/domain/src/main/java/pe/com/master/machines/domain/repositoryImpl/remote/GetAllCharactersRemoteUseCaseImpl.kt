package pe.com.master.machines.domain.repositoryImpl.remote

import pe.com.master.machines.data.repository.remote.StoryCharacterRemoteDataRepository
import pe.com.master.machines.domain.repository.remote.GetAllCharactersRemoteUseCase
import javax.inject.Inject

class GetAllCharactersRemoteUseCaseImpl @Inject constructor(
    private val storyCharacterRemoteDataRepository: StoryCharacterRemoteDataRepository
) : GetAllCharactersRemoteUseCase {

    override fun invoke(page: Int) = storyCharacterRemoteDataRepository.getLoadAllCharacters(page)
}