package pe.com.master.machines.data.repositoryImpl.local.database

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.common.response.toErrorType
import pe.com.master.machines.data.mappers.asModel
import pe.com.master.machines.data.mappers.toEntity
import pe.com.master.machines.data.repository.database.StoryCharacterLocalDataRepository
import pe.com.master.machines.database.repository.StoryCharactersDatabaseRepository
import pe.com.master.machines.model.model.StoryCharacter
import javax.inject.Inject

class StoryCharacterLocalDataRepositoryImpl @Inject constructor(private val storyCharactersDatabaseRepository: StoryCharactersDatabaseRepository) :
    StoryCharacterLocalDataRepository {

    override fun saveAllCharacters(entities: List<StoryCharacter>) =
        storyCharactersDatabaseRepository.saveAllCharacters(entities.toEntity()).map { res ->
            when (res) {
                is Resource.Error -> Resource.Error(res.error)
                is Resource.Success -> Resource.Success(res.data)
            }
        }.catch { emit(Resource.Error(it.toErrorType())) }

    override fun getSingleCharacter(id: Int) =
        storyCharactersDatabaseRepository.getSingleCharacter(id).map { res ->
            when (res) {
                is Resource.Error -> Resource.Error(res.error)
                is Resource.Success -> Resource.Success(res.data.asModel())
            }
        }.catch { emit(Resource.Error(it.toErrorType())) }

    override fun getCharactersByPage(
        limit: Int, offset: Int
    ) = storyCharactersDatabaseRepository.getCharactersByPage(limit, offset).map { res ->
        when (res) {
            is Resource.Error -> Resource.Error(res.error)
            is Resource.Success -> Resource.Success(res.data.asModel())
        }
    }.catch { emit(Resource.Error(it.toErrorType())) }

    override fun searchCharacterByName(
        limit: Int, offset: Int, name: String, status: String
    ) = storyCharactersDatabaseRepository.searchCharacterByName(limit, offset, name, status)
        .map { res ->
            when (res) {
                is Resource.Error -> Resource.Error(res.error)
                is Resource.Success -> Resource.Success(res.data.asModel())
            }
        }.catch { emit(Resource.Error(it.toErrorType())) }
}