package pe.com.master.machines.domain.repository.remote

import kotlinx.coroutines.flow.Flow
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.model.model.Episode

interface GetEpisodesByIdsRemoteUseCase {

    operator fun invoke(ids: String): Flow<Resource<List<Episode>>>
}
