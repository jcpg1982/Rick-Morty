package pe.com.master.machines.network.model.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import pe.com.master.machines.network.model.model.InfoNetwork
import pe.com.master.machines.network.model.model.CharacterNetwork

@Serializable
data class ResponseAllCharacterNetwork(
    @SerializedName("info")
    val info: InfoNetwork? = null,
    @SerializedName("results")
    val results: List<CharacterNetwork>? = null
)