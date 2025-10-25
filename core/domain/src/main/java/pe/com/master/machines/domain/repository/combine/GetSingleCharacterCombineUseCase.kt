package pe.com.master.machines.domain.repository.combine

import kotlinx.coroutines.flow.Flow
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.model.model.StoryCharacter

interface GetSingleCharacterCombineUseCase {
    operator fun invoke(isHaveInternet: Boolean, id: Int): Flow<Resource<StoryCharacter>>
}
