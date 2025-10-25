package pe.com.master.machines.domain.repository.database

import kotlinx.coroutines.flow.Flow
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.model.model.StoryCharacter

interface SaveAllCharactersLocalUseCase {
    operator fun invoke(entities: List<StoryCharacter>): Flow<Resource<Unit>>
}
