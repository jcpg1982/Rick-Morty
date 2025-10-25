package pe.com.master.machines.data.repositoryImpl.remote

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.common.response.toErrorType
import pe.com.master.machines.data.mappers.asModel
import pe.com.master.machines.data.repository.remote.StoryCharacterRemoteDataRepository
import pe.com.master.machines.network.repository.StoryCharacterNetworkRepository
import javax.inject.Inject

class StoryCharacterRemoteDataRepositoryImpl @Inject constructor(
    private val storyCharacterNetworkRepository: StoryCharacterNetworkRepository
) : StoryCharacterRemoteDataRepository {

    override fun getLoadAllCharacters(page: Int) =
        storyCharacterNetworkRepository.getLoadAllCharacters(page)
            .map { res ->
                when (res) {
                    is Resource.Error<*> -> Resource.Error(res.error)
                    is Resource.Success -> Resource.Success(res.data.asModel())
                }
            }
            .catch { emit(Resource.Error(it.toErrorType())) }

    override fun getLoadSingleCharacter(id: Int) =
        storyCharacterNetworkRepository.getLoadSingleCharacter(id)
            .map { res ->
                when (res) {
                    is Resource.Error<*> -> Resource.Error(res.error)
                    is Resource.Success -> Resource.Success(res.data.asModel())
                }
            }
            .catch { emit(Resource.Error(it.toErrorType())) }

    override fun searchCharacterByName(
        page: Int, name: String, status: String
    ) = storyCharacterNetworkRepository.searchCharacterByName(page, name, status)
        .map { res ->
            when (res) {
                is Resource.Error<*> -> Resource.Error(res.error)
                is Resource.Success -> Resource.Success(res.data.asModel())
            }
        }
        .catch { emit(Resource.Error(it.toErrorType())) }

    override fun getEpisodesByIds(ids: String) =
        storyCharacterNetworkRepository.getEpisodesByIds(ids)
            .map { res ->
                when (res) {
                    is Resource.Error<*> -> Resource.Error(res.error)
                    is Resource.Success -> Resource.Success(res.data.asModel())
                }
            }
            .catch { emit(Resource.Error(it.toErrorType())) }

}