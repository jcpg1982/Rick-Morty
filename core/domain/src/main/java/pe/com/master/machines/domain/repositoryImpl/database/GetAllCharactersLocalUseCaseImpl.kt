package pe.com.master.machines.domain.repositoryImpl.database

import pe.com.master.machines.data.repository.database.StoryCharacterLocalDataRepository
import pe.com.master.machines.domain.repository.database.GetAllCharactersLocalUseCase
import javax.inject.Inject

class GetAllCharactersLocalUseCaseImpl @Inject constructor(
    private val storyCharacterLocalDataRepository: StoryCharacterLocalDataRepository
) : GetAllCharactersLocalUseCase {

    override fun invoke(limit: Int, offset: Int) =
        storyCharacterLocalDataRepository.getCharactersByPage(limit, offset)
}