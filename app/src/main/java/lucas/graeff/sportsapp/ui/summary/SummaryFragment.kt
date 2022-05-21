package lucas.graeff.sportsapp.ui.summary

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import lucas.graeff.sportsapp.R
import lucas.graeff.sportsapp.ui.playerProfile.PlayerProfileViewModel

class SummaryFragment: Fragment(R.layout.fragment_summary) {
    private val playerProfileViewModel: PlayerProfileViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.txt_summary).text = playerProfileViewModel.getPlayer().strDescriptionEN
    }
}