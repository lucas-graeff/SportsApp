package lucas.graeff.sportsapp.ui.playerProfile

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import lucas.graeff.sportsapp.R
import lucas.graeff.sportsapp.adapters.ViewPagerAdapter
import lucas.graeff.sportsapp.databinding.FragmentPlayerProfileBinding
import lucas.graeff.sportsapp.models.Player
import lucas.graeff.sportsapp.ui.home.HomeViewModel

@AndroidEntryPoint
class PlayerProfileFragment : Fragment(R.layout.fragment_player_profile) {
    private lateinit var binding: FragmentPlayerProfileBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private val playerProfileViewModel: PlayerProfileViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.actionBar?.setHomeButtonEnabled(true)
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_player_profile,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Handles exit button
        binding.topAppBar.getChildAt(1).setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        val player: Player = playerProfileViewModel.getPlayer()
        Picasso.get().load(player.strThumb).into(binding.imgThumbnail)
        binding.txtPlayerName.text = player.strPlayer
        binding.txtSport.text = player.strSport


        binding.viewPager.adapter = ViewPagerAdapter(requireActivity())
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, index ->
            tab.text = when(index){
                0 -> {"Summary"}
                1 -> {"Bio"}
                else -> {
                    throw Resources.NotFoundException("Position not found")
                }
            }
        }.attach()

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

}