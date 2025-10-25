package pe.com.master.machines.data.mappers

import pe.com.master.machines.model.model.Info
import pe.com.master.machines.model.response.ResponseAllCharacter
import pe.com.master.machines.network.model.response.ResponseAllCharacterNetwork

fun ResponseAllCharacterNetwork.asModel() = ResponseAllCharacter(
    info = this.info?.asModel() ?: Info(),
    results = this.results.asModel(),
)