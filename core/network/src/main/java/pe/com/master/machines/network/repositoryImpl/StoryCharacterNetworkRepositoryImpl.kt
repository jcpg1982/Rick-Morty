package pe.com.master.machines.network.repositoryImpl

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.flow
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.common.response.toErrorType
import pe.com.master.machines.network.api.ApiService
import pe.com.master.machines.network.model.model.EpisodeNetwork
import pe.com.master.machines.network.repository.StoryCharacterNetworkRepository
import javax.inject.Inject

class StoryCharacterNetworkRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    StoryCharacterNetworkRepository {

    override fun getLoadAllCharacters(page: Int) = flow {
        val response = apiService.getLoadAllCharacters(page)
        emit(Resource.Success(response))
    }

    override fun getLoadSingleCharacter(id: Int) = flow {
        val response = apiService.getLoadSingleCharacter(id)
        emit(Resource.Success(response))
    }

    override fun searchCharacterByName(
        page: Int, name: String, status: String
    ) = flow {
        val response = apiService.searchCharacterByName(page, name, status)
        emit(Resource.Success(response))
    }

    override fun getEpisodesByIds(ids: String) = flow {
        val response = apiService.getEpisodesByIds(ids)
        if (response.isSuccessful) {
            val jsonElement = response.body()
            if (jsonElement != null) {
                val gson = Gson()
                val episodesList: List<EpisodeNetwork> = if (jsonElement.isJsonArray) {
                    val listType = object : TypeToken<List<EpisodeNetwork>>() {}.type
                    gson.fromJson(jsonElement, listType)
                } else {
                    val singleEpisode = gson.fromJson(jsonElement, EpisodeNetwork::class.java)
                    listOf(singleEpisode)
                }
                emit(Resource.Success(episodesList))
            } else {
                emit(Resource.Error(Throwable("La respuesta no tiene contenido").toErrorType()))
            }
        } else {
            emit(Resource.Error(Throwable("Error en la llamada a la API: ${response.code()}").toErrorType()))

        }
    }
}