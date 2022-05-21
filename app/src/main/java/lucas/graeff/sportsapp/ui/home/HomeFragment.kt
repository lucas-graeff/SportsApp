package lucas.graeff.sportsapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import lucas.graeff.sportsapp.R
import lucas.graeff.sportsapp.adapters.PlayersAdapter
import lucas.graeff.sportsapp.databinding.FragmentHomeBinding
import lucas.graeff.sportsapp.ui.playerProfile.PlayerProfileFragment
import lucas.graeff.sportsapp.ui.playerProfile.PlayerProfileViewModel


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private val playerProfileViewModel: PlayerProfileViewModel by activityViewModels()
    private var job: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
        }

        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        homeViewModel.players().observe(viewLifecycleOwner) {
            recyclerView.adapter = PlayersAdapter(it.player) { player -> Log.d("Test", player.strPlayer)
                playerProfileViewModel.setPlayer(player)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment, PlayerProfileFragment()).addToBackStack("Home").commit()
            }
        }


        binding.searchText.addTextChangedListener {
            job?.cancel()
            job = lifecycleScope.launch(Dispatchers.IO) {
                // API can only be called once every 2 seconds
                delay(2000)
                homeViewModel.getPlayers("$it")
            }

        }

    }
}