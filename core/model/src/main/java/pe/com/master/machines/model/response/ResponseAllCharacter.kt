package pe.com.master.machines.model.response

import pe.com.master.machines.model.model.StoryCharacter
import pe.com.master.machines.model.model.Info


data class ResponseAllCharacter(
    val info: Info,
    val results: List<StoryCharacter>
)