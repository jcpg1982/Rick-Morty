package pe.com.master.machines.detail_character.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.common.utils.messageError
import pe.com.master.machines.domain.repository.combine.GetSingleCharacterCombineUseCase
import pe.com.master.machines.domain.repository.remote.GetEpisodesByIdsRemoteUseCase
import javax.inject.Inject

@HiltViewModel
class DetailCharacterViewmodel @Inject constructor(
    private val getSingleCharacterCombineUseCase: GetSingleCharacterCombineUseCase,
    private val getEpisodesByIdsRemoteUseCase: GetEpisodesByIdsRemoteUseCase,
) : ViewModel() {
    private val TAG = DetailCharacterViewmodel::class.java.simpleName

    private val _getStoryCharactersState: MutableStateFlow<DetailCharacterState> =
        MutableStateFlow(DetailCharacterState.First)
    val getStoryCharactersState = _getStoryCharactersState.asStateFlow()

    var isConnected = false

    fun getStoryCharacters(characterId:Int) {
        viewModelScope.launch {
            getSingleCharacterCombineUseCase.invoke(isConnected, characterId)
                .flowOn(Dispatchers.IO)
                .onStart {
                    Log.i(TAG, "getStoryCharacters onStart")
                    _getStoryCharactersState.update { DetailCharacterState.Loading }
                }
                .catch { e ->
                    Log.i(TAG, "getStoryCharacters catch ${e.message}")
                    _getStoryCharactersState.update { DetailCharacterState.Error(e) }
                }
                .collect { res ->
                    when (res) {
                        is Resource.Error -> {
                            Log.i(TAG, "getStoryCharacters collect error ${res.messageError}")
                            _getStoryCharactersState.update { DetailCharacterState.Error(Throwable(res.messageError)) }
                        }

                        is Resource.Success -> {
                            Log.i(TAG, "getStoryCharacters collect Success ${res.data}")
                            _getStoryCharactersState.update { DetailCharacterState.Success(res.data) }
                        }
                    }
                }
        }
    }

    /*fun getObtainEpisodes(dataList: List<String>) {
        viewModelScope.launch {
            val episodeIds = dataList.map { url ->
                url.substringAfterLast('/')
            }
            val idsString = episodeIds.joinToString(",")
            getEpisodesByIdsRemoteUseCase.invoke(idsString)
                .flowOn(Dispatchers.IO)
                .onStart {
                    Log.i(TAG, "getObtainEpisodes onStart")
                    _getStoryCharactersState.update { DetailCharacterState.Loading }
                }
                .catch { e ->
                    Log.i(TAG, "getObtainEpisodes catch ${e.message}")
                    _getStoryCharactersState.update { DetailCharacterState.Error(e) }
                }
                .collect { res ->
                    when (res) {
                        is Resource.Error -> {
                            Log.i(TAG, "getObtainEpisodes collect error ${res.messageError}")
                            _getStoryCharactersState.update { DetailCharacterState.Error(Throwable(res.messageError)) }
                        }

                        is Resource.Success -> {
                            Log.i(TAG, "getObtainEpisodes collect Success ${res.data}")
                            _getStoryCharactersState.update { DetailCharacterState.Success(res.data) }
                        }
                    }
                }
        }
    }*/
}