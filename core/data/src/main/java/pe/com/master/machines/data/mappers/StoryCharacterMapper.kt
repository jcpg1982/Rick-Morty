package pe.com.master.machines.data.mappers

import pe.com.master.machines.database.entity.StoryCharacterEntity
import pe.com.master.machines.model.model.StoryCharacter
import pe.com.master.machines.model.utils.Utils.fromStringList

fun StoryCharacter.toEntity(): StoryCharacterEntity {
    val entity = StoryCharacterEntity()
    entity.id = this.id
    entity.name = this.name
    entity.status = this.status
    entity.species = this.species
    entity.type = this.type
    entity.gender = this.gender
    entity.originName = this.originName
    entity.originUrl = this.originUrl
    entity.locationName = this.locationName
    entity.locationUrl = this.locationUrl
    entity.image = this.image
    entity.episode = fromStringList(this.episode)
    entity.url = this.url
    entity.created = this.created
    return entity
}

fun List<StoryCharacter>?.toEntity() = this?.map(StoryCharacter::toEntity) ?: listOf()
