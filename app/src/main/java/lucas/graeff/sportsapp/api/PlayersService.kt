package lucas.graeff.sportsapp.api

import lucas.graeff.sportsapp.models.PlayerList
import retrofit2.http.GET
import retrofit2.http.Query

interface PlayersService {

    @GET("searchplayers.php?")
    suspend fun getPlayers(@Query(value="p", encoded = true) name: String): PlayerList
}