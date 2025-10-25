package pe.com.master.machines.network.model.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class InfoNetwork(
    @SerializedName("count")
    val count: Int? = null,
    @SerializedName("next")
    val next: String? = null,
    @SerializedName("pages")
    val pages: Int? = null,
    @SerializedName("prev")
    val prev: String? = null
)