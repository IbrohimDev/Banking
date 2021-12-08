package uz.gita.banking.presentation.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gita.banking.presentation.ui.pages.*

class BottomAttachPagerAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MainPage()
            1 -> TransferPage()
            2 -> PaymentPage()
            3 -> ServicePage()
            else -> IncomePage()
        }
    }


}