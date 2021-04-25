package org.hiro.financeapp.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.hiro.financeapp.ui.intro.SliderItemFragment

class SliderIntroAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int) = SliderItemFragment.newInstance(position)
}