package pe.com.master.machines.model.model


data class StoryCharacter(
    val id: Int = -1,
    val name: String = "",
    val status: String = "",
    val species: String = "",
    val type: String = "",
    val gender: String = "",
    val originName: String = "",
    val originUrl: String = "",
    val locationName: String = "",
    val locationUrl: String = "",
    val image: String = "",
    val episode: List<String> = listOf(),
    val url: String = "",
    val created: String = ""
)