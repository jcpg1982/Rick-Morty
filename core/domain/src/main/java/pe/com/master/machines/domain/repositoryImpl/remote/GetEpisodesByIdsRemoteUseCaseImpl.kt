package pe.com.master.machines.domain.repositoryImpl.remote

import pe.com.master.machines.data.repository.remote.StoryCharacterRemoteDataRepository
import pe.com.master.machines.domain.repository.remote.GetEpisodesByIdsRemoteUseCase
import javax.inject.Inject

class GetEpisodesByIdsRemoteUseCaseImpl @Inject constructor(
    private val storyCharacterRemoteDataRepository: StoryCharacterRemoteDataRepository
) : GetEpisodesByIdsRemoteUseCase {
    override fun invoke(ids: String) = storyCharacterRemoteDataRepository.getEpisodesByIds(ids)
}