package lucas.graeff.sportsapp.ui.playerProfile

import androidx.lifecycle.ViewModel
import lucas.graeff.sportsapp.models.Player

class PlayerProfileViewModel : ViewModel() {
    private lateinit var player: Player

    fun setPlayer(newPlayer: Player) {
        player = newPlayer
    }

    fun getPlayer() = player
}