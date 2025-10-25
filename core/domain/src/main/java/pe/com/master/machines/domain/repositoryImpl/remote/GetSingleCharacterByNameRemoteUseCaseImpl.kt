package pe.com.master.machines.domain.repositoryImpl.remote

import pe.com.master.machines.data.repository.remote.StoryCharacterRemoteDataRepository
import pe.com.master.machines.domain.repository.remote.GetSingleCharacterByNameRemoteUseCase
import javax.inject.Inject

class GetSingleCharacterByNameRemoteUseCaseImpl @Inject constructor(
    private val storyCharacterRemoteDataRepository: StoryCharacterRemoteDataRepository
) : GetSingleCharacterByNameRemoteUseCase {

    override fun invoke(
        page: Int, name: String, status: String
    ) = storyCharacterRemoteDataRepository.searchCharacterByName(page, name, status)
}