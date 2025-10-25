package pe.com.master.machines.network.api

import com.google.gson.JsonElement
import pe.com.master.machines.network.model.model.CharacterNetwork
import pe.com.master.machines.network.model.response.ResponseAllCharacterNetwork
import pe.com.master.machines.network.utils.Utils.Endpoints.ALL_CHARACTER
import pe.com.master.machines.network.utils.Utils.Endpoints.EPISODES
import pe.com.master.machines.network.utils.Utils.Endpoints.SINGLE_CHARACTER
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(ALL_CHARACTER)
    suspend fun getLoadAllCharacters(@Query("page") page: Int): ResponseAllCharacterNetwork

    @GET("$SINGLE_CHARACTER{id}")
    suspend fun getLoadSingleCharacter(@Path("id") id: Int): CharacterNetwork

    @GET(SINGLE_CHARACTER)
    suspend fun searchCharacterByName(
        @Query("page") page: Int,
        @Query("name") name: String,
        @Query("status") status: String = "alive"
    ): ResponseAllCharacterNetwork

    @GET("$EPISODES{ids}")
    suspend fun getEpisodesByIds(@Path("ids") ids: String): Response<JsonElement>
}