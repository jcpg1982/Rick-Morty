package pe.com.master.machines.data.mappers

import pe.com.master.machines.model.model.Episode
import pe.com.master.machines.network.model.model.EpisodeNetwork

fun EpisodeNetwork.asModel() = Episode(
    airDate = this.airDate.orEmpty(),
    characters = this.characters ?: listOf(),
    created = this.created.orEmpty(),
    episode = this.episode.orEmpty(),
    id = this.id ?: -1,
    name = this.name.orEmpty(),
    url = this.url.orEmpty(),
)

fun List<EpisodeNetwork>?.asModel() = this?.map(EpisodeNetwork::asModel) ?: listOf()
