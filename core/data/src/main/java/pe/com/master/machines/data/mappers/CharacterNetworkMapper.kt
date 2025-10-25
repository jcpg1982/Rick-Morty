package pe.com.master.machines.data.mappers

import pe.com.master.machines.model.model.StoryCharacter
import pe.com.master.machines.network.model.model.CharacterNetwork

fun CharacterNetwork.asModel() = StoryCharacter(
    id = this.id ?: -1,
    name = this.name.orEmpty(),
    status = this.status.orEmpty(),
    species = this.species.orEmpty(),
    type = this.type.orEmpty(),
    gender = this.gender.orEmpty(),
    originName = this.origin?.name.orEmpty(),
    originUrl = this.origin?.url.orEmpty(),
    locationName = this.location?.name.orEmpty(),
    locationUrl = this.location?.url.orEmpty(),
    image = this.image.orEmpty(),
    episode = this.episode.orEmpty(),
    url = this.url.orEmpty(),
    created = this.created.orEmpty(),
)

fun List<CharacterNetwork>?.asModel() = this?.map(CharacterNetwork::asModel) ?: listOf()