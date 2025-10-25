package pe.com.master.machines.data.mappers

import pe.com.master.machines.database.entity.StoryCharacterEntity
import pe.com.master.machines.model.model.StoryCharacter
import pe.com.master.machines.model.utils.Utils.toStringList

fun StoryCharacterEntity.asModel() = StoryCharacter(
    id = this.id,
    name = this.name,
    status = this.status,
    species = this.species,
    type = this.type,
    gender = this.gender,
    originName = this.originName,
    originUrl = this.originUrl,
    locationName = this.locationName,
    locationUrl = this.locationUrl,
    image = this.image,
    episode = toStringList(this.episode),
    url = this.url,
    created = this.created,
)

fun List<StoryCharacterEntity>?.asModel() = this?.map(StoryCharacterEntity::asModel) ?: listOf()