package pe.com.master.machines.home.viewmodel

import pe.com.master.machines.model.response.ResponseAllCharacter

sealed interface HomeState {

    data object First : HomeState
    data object Loading : HomeState
    data class Error(val throwable: Throwable) : HomeState
    data class SuccessAllCharacter(val data: ResponseAllCharacter) : HomeState
    data class SuccessSingleCharacterByName(val data: ResponseAllCharacter) : HomeState
}