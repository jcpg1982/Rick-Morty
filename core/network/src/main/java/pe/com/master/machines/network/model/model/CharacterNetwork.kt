package pe.com.master.machines.network.model.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterNetwork(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("species")
    val species: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("gender")
    val gender: String? = null,
    @SerializedName("origin")
    val origin: OriginNetwork? = null,
    @SerializedName("location")
    val location: LocationNetwork? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("episode")
    val episode: List<String>? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("created")
    val created: String? = null
)