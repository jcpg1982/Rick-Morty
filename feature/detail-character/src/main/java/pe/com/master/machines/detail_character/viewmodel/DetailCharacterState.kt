package pe.com.master.machines.detail_character.viewmodel

import pe.com.master.machines.model.model.StoryCharacter

sealed interface DetailCharacterState {

    data object First : DetailCharacterState
    data object Loading : DetailCharacterState
    data class Error(val throwable: Throwable) : DetailCharacterState
    data class Success(val data: StoryCharacter) : DetailCharacterState
}