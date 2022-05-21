package lucas.graeff.sportsapp.ui.bio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import lucas.graeff.sportsapp.R
import lucas.graeff.sportsapp.databinding.FragmentBioBinding
import lucas.graeff.sportsapp.ui.playerProfile.PlayerProfileViewModel

@AndroidEntryPoint
class BioFragment: Fragment(R.layout.fragment_bio) {
    lateinit var binding: FragmentBioBinding
    private val playerProfileViewModel: PlayerProfileViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_bio,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.player = playerProfileViewModel.getPlayer()
    }
}