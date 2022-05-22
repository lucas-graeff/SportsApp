package lucas.graeff.sportsapp.ui.playerProfile

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import lucas.graeff.sportsapp.R
import lucas.graeff.sportsapp.adapters.ViewPagerAdapter
import lucas.graeff.sportsapp.databinding.FragmentPlayerProfileBinding
import lucas.graeff.sportsapp.models.Player

@AndroidEntryPoint
class PlayerProfileFragment : Fragment(R.layout.fragment_player_profile) {
    private lateinit var binding: FragmentPlayerProfileBinding
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
        binding.topAppBar.getChildAt(0).setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        val player: Player = playerProfileViewModel.getPlayer()
        binding.player = player

        Picasso.get().load(player.strThumb).into(binding.imgThumbnail)


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
        
    }

}