package pe.com.master.machines.data.repository.remote

import kotlinx.coroutines.flow.Flow
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.model.model.Episode
import pe.com.master.machines.model.model.StoryCharacter
import pe.com.master.machines.model.response.ResponseAllCharacter
import pe.com.master.machines.network.model.model.EpisodeNetwork

interface StoryCharacterRemoteDataRepository {
    fun getLoadAllCharacters(page: Int): Flow<Resource<ResponseAllCharacter>>
    fun getLoadSingleCharacter(id: Int): Flow<Resource<StoryCharacter>>
    fun searchCharacterByName(
        page: Int, name: String, status: String
    ): Flow<Resource<ResponseAllCharacter>>
    fun getEpisodesByIds(ids: String): Flow<Resource<List<Episode>>>
}