package pe.com.master.machines.database.repositoryImpl

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import jakarta.inject.Inject
import kotlinx.coroutines.flow.flowOf
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.database.entity.StoryCharacterEntity
import pe.com.master.machines.database.repository.StoryCharactersDatabaseRepository


class StoryCharactersDatabaseRepositoryImpl @Inject constructor(private val realmConfig: RealmConfiguration) :
    StoryCharactersDatabaseRepository {

    private val TAG = StoryCharactersDatabaseRepositoryImpl::class.java.simpleName

    private val realm: Realm
        get() = Realm.open(realmConfig)

    override fun saveAllCharacters(entities: List<StoryCharacterEntity>) =
        flowOf(Resource.Success(Unit))
    /*flow {
    val result = daoStoryCharacterEntity.saveCharacters(entities)
    emit(Resource.Success(result))
}*/

    override fun getSingleCharacter(id: Int) = flowOf(Resource.Success(StoryCharacterEntity()))
    /*flow {
    val result = daoStoryCharacterEntity.getSingleCharacter(id)
    emit(Resource.Success(result))
}*/

    override fun getCharactersByPage(limit: Int, offset: Int) =
        flowOf(Resource.Success(listOf<StoryCharacterEntity>()))
    /*flow {
    val result = daoStoryCharacterEntity.getCharactersByPage(limit, offset)
    emit(Resource.Success(result))
}*/

    override fun searchCharacterByName(
        limit: Int, offset: Int, name: String, status: String
    ) = flowOf(Resource.Success(listOf<StoryCharacterEntity>()))
    /*flow {
        val result = daoStoryCharacterEntity.searchCharacterByName(limit, offset, name, status)
        emit(Resource.Success(result))
    }*/
}