package lucas.graeff.sportsapp.adapters

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import lucas.graeff.sportsapp.ui.bio.BioFragment
import lucas.graeff.sportsapp.ui.summary.SummaryFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                SummaryFragment()
            }
            1 -> {
                BioFragment()
            }
            else -> {
                throw Resources.NotFoundException("Position not found")
            }
        }
    }

}