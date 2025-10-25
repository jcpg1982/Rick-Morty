package pe.com.master.machines.database.repository

import kotlinx.coroutines.flow.Flow
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.database.entity.StoryCharacterEntity

interface StoryCharactersDatabaseRepository {

    fun saveAllCharacters(entities: List<StoryCharacterEntity>): Flow<Resource<Unit>>
    fun getSingleCharacter(id: Int): Flow<Resource<StoryCharacterEntity>>
    fun getCharactersByPage(limit: Int, offset: Int): Flow<Resource<List<StoryCharacterEntity>>>
    fun searchCharacterByName(
        limit: Int, offset: Int, name: String, status: String
    ): Flow<Resource<List<StoryCharacterEntity>>>
}