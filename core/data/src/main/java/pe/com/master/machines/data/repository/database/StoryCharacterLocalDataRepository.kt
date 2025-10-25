package pe.com.master.machines.data.repository.database

import kotlinx.coroutines.flow.Flow
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.model.model.StoryCharacter

interface StoryCharacterLocalDataRepository {

    fun saveAllCharacters(entities: List<StoryCharacter>): Flow<Resource<Unit>>
    fun getSingleCharacter(id: Int): Flow<Resource<StoryCharacter>>
    fun getCharactersByPage(limit: Int, offset: Int): Flow<Resource<List<StoryCharacter>>>
    fun searchCharacterByName(
        limit: Int, offset: Int, name: String, status: String
    ): Flow<Resource<List<StoryCharacter>>>
}