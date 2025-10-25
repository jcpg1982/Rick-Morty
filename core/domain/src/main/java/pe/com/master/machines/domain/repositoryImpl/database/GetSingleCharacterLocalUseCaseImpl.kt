package pe.com.master.machines.domain.repositoryImpl.database

import pe.com.master.machines.data.repository.database.StoryCharacterLocalDataRepository
import pe.com.master.machines.domain.repository.database.GetSingleCharacterLocalUseCase
import javax.inject.Inject

class GetSingleCharacterLocalUseCaseImpl @Inject constructor(
    private val storyCharacterLocalDataRepository: StoryCharacterLocalDataRepository
) : GetSingleCharacterLocalUseCase {

    override fun invoke(id: Int) = storyCharacterLocalDataRepository.getSingleCharacter(id)

}