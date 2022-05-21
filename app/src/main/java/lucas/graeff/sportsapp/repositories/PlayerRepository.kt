package lucas.graeff.sportsapp.repositories

import lucas.graeff.sportsapp.api.PlayersService
import lucas.graeff.sportsapp.models.PlayerList
import javax.inject.Inject

class PlayerRepository @Inject constructor(private val service: PlayersService) {
    suspend fun getPlayers(name: String = ""): PlayerList {
        return service.getPlayers(name)
    }
}
