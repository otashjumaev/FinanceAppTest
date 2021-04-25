package org.hiro.financeapp.ui.main

import android.os.Bundle
import android.view.View
import org.hiro.financeapp.R
import org.hiro.financeapp.databinding.FragmentMainBinding
import org.hiro.financeapp.model.UserMini
import org.hiro.financeapp.ui.CardsFragment
import org.hiro.financeapp.ui.EarningsFragment
import org.hiro.financeapp.ui.HomeFragment
import org.hiro.financeapp.ui.base.BaseFragment
import org.hiro.financeapp.util.getData

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val pages = arrayOf(HomeFragment(), EarningsFragment(), CardsFragment())
    private var activePage = -1
    private lateinit var user: UserMini

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBottomNavBar()
        closeKeyboard()
        navigateTo(0)
    }

    private fun initBottomNavBar() {
        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.homePage -> {
                    navigateTo(0)
                }
                R.id.cardsPage -> {
                    navigateTo(1)
                }
                R.id.earningsPage -> {
                    navigateTo(2)
                }
                R.id.profilePage -> {
                    showSnackBar("Not available")
                }
            }
            true
        }
    }

    private fun navigateTo(i: Int, bundle: Bundle? = null) {
        if (activePage != i) {
            activePage = i
            bundle?.let {
                pages[i].arguments = bundle
            }
            childFragmentManager
                .beginTransaction()
                .replace(R.id.container, pages[i])
                .commit()
        }
    }


}