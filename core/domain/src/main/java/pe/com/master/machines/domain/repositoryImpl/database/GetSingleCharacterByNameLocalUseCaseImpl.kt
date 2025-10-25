package pe.com.master.machines.domain.repositoryImpl.database

import pe.com.master.machines.data.repository.database.StoryCharacterLocalDataRepository
import pe.com.master.machines.domain.repository.database.GetSingleCharacterByNameLocalUseCase
import javax.inject.Inject

class GetSingleCharacterByNameLocalUseCaseImpl @Inject constructor(
    private val storyCharacterLocalDataRepository: StoryCharacterLocalDataRepository
) : GetSingleCharacterByNameLocalUseCase {

    override fun invoke(
        limit: Int, offset: Int, name: String, status: String
    ) = storyCharacterLocalDataRepository.searchCharacterByName(limit, offset, name, status)

}