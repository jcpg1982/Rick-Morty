package pe.com.master.machines.domain.repositoryImpl.database

import pe.com.master.machines.data.repository.database.StoryCharacterLocalDataRepository
import pe.com.master.machines.domain.repository.database.SaveAllCharactersLocalUseCase
import pe.com.master.machines.model.model.StoryCharacter
import javax.inject.Inject

class SaveAllCharactersLocalUseCaseImpl @Inject constructor(
    private val storyCharacterLocalDataRepository: StoryCharacterLocalDataRepository
) : SaveAllCharactersLocalUseCase {

    override fun invoke(entities: List<StoryCharacter>) =
        storyCharacterLocalDataRepository.saveAllCharacters(entities)

}