package org.hiro.financeapp.ui.intro

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import org.hiro.financeapp.R
import org.hiro.financeapp.adapter.SliderIntroAdapter
import org.hiro.financeapp.databinding.FragmentIntroBinding
import org.hiro.financeapp.ui.base.BaseFragment
import org.hiro.financeapp.viewmodel.IntroViewModel

class IntroFragment : BaseFragment<FragmentIntroBinding>(FragmentIntroBinding::inflate) {
    private lateinit var viewModel: IntroViewModel
    private lateinit var adapter: SliderIntroAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(IntroViewModel::class.java)

        adapter = SliderIntroAdapter(this)
        binding.pagerIntroSlider.adapter = adapter
        binding.pagerIntroSlider.isSaveEnabled = false
        TabLayoutMediator(binding.tabs, binding.pagerIntroSlider) { _, _ -> }.attach()
        changeStatusBarColor()
        initListeners()
    }

    private fun initListeners() {
        binding.skipBtn.setOnClickListener {
            findNavController().popBackStack()
            findNavController().navigate(R.id.fragmentSignIn)
        }
        binding.nextBtn.setOnClickListener {
            if (binding.pagerIntroSlider.currentItem < adapter.itemCount - 1)
                binding.pagerIntroSlider.currentItem = binding.pagerIntroSlider.currentItem + 1
            else {
                findNavController().popBackStack()
                findNavController().navigate(R.id.fragmentSignIn)
            }
        }

        binding.pagerIntroSlider.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == adapter.itemCount - 1) {
                    binding.nextBtn.setText(R.string.get_started)
                } else {
                    binding.nextBtn.setText(R.string.next)
                }
            }
        })
    }

    private fun changeStatusBarColor() {
        val window = requireActivity().window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
    }

}