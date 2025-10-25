package pe.com.master.machines.network.model.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class OriginNetwork(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("url")
    val url: String? = null,
)