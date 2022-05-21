package lucas.graeff.sportsapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lucas.graeff.sportsapp.models.PlayerList
import lucas.graeff.sportsapp.repositories.PlayerRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: PlayerRepository) : ViewModel() {
    private var players = MutableLiveData<PlayerList>()

    init {
        getPlayers()
    }

    fun getPlayers(name: String = "") {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getPlayers(name)
            players.postValue(response)
        }
    }

    fun players() = players
}