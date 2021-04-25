package org.hiro.financeapp.ui.intro

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.navigation.fragment.findNavController
import org.hiro.financeapp.R
import org.hiro.financeapp.databinding.FragmentSplashScreenBinding
import org.hiro.financeapp.model.User
import org.hiro.financeapp.ui.base.BaseFragment
import org.hiro.financeapp.util.HawkUtils
import org.hiro.financeapp.util.putData

class SplashFragment :
    BaseFragment<FragmentSplashScreenBinding>(FragmentSplashScreenBinding::inflate) {

    private var counter: CountDownTimer? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loader()
    }

    private fun loader() {

        counter = object : CountDownTimer(1000, 1000) {
            override fun onFinish() {
                findNavController().popBackStack()
                when {
                    HawkUtils.firstOpened -> {
                        HawkUtils.firstOpened = false
                        findNavController().navigate(R.id.introFragment)
                    }
                    HawkUtils.userLoggedIn -> {
                        findNavController().navigate(R.id.mainFragment)
                    }
                    else -> {
                        findNavController().navigate(R.id.fragmentSignIn)
                    }
                }
            }

            override fun onTick(millisUntilFinished: Long) {
            }
        }.start()
    }
}