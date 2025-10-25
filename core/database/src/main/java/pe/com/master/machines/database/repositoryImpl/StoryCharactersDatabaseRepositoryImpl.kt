package pe.com.master.machines.database.repositoryImpl

import jakarta.inject.Inject
import kotlinx.coroutines.flow.flow
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.database.database.DataBase
import pe.com.master.machines.database.entity.StoryCharacterEntity
import pe.com.master.machines.database.repository.StoryCharactersDatabaseRepository


class StoryCharactersDatabaseRepositoryImpl @Inject constructor(database: DataBase) :
    StoryCharactersDatabaseRepository {

    private val TAG = StoryCharactersDatabaseRepositoryImpl::class.java.simpleName

    private val daoStoryCharacterEntity = database.daoStoryCharacterEntity()

    override fun saveAllCharacters(entities: List<StoryCharacterEntity>) = flow {
        val result = daoStoryCharacterEntity.saveCharacters(entities)
        emit(Resource.Success(result))
    }

    override fun getSingleCharacter(id: Int) = flow {
        val result = daoStoryCharacterEntity.getSingleCharacter(id)
        emit(Resource.Success(result))
    }

    override fun getCharactersByPage(limit: Int, offset: Int) = flow {
        val result = daoStoryCharacterEntity.getCharactersByPage(limit, offset)
        emit(Resource.Success(result))
    }

    override fun searchCharacterByName(
        limit: Int, offset: Int, name: String, status: String
    ) = flow {
        val result = daoStoryCharacterEntity.searchCharacterByName(limit, offset, name, status)
        emit(Resource.Success(result))
    }
}