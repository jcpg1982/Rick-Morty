package pe.com.master.machines.network.repository

import kotlinx.coroutines.flow.Flow
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.network.model.model.CharacterNetwork
import pe.com.master.machines.network.model.model.EpisodeNetwork
import pe.com.master.machines.network.model.response.ResponseAllCharacterNetwork

interface StoryCharacterNetworkRepository {
    fun getLoadAllCharacters(page: Int): Flow<Resource<ResponseAllCharacterNetwork>>

    fun getLoadSingleCharacter(id: Int): Flow<Resource<CharacterNetwork>>

    fun searchCharacterByName(
        page: Int, name: String, status: String
    ): Flow<Resource<ResponseAllCharacterNetwork>>

    fun getEpisodesByIds(ids: String): Flow<Resource<List<EpisodeNetwork>>>
}