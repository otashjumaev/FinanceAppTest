package org.hiro.financeapp.ui.intro

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import org.hiro.financeapp.databinding.ItemSliderFragmenrBinding
import org.hiro.financeapp.ui.base.BaseFragment
import org.hiro.financeapp.viewmodel.SliderItemViewModel

class SliderItemFragment :
    BaseFragment<ItemSliderFragmenrBinding>(ItemSliderFragmenrBinding::inflate) {
    private lateinit var viewModel: SliderItemViewModel

    companion object {
        private const val ARG_KEY_POS = "slider-position"

        fun newInstance(position: Int): SliderItemFragment {
            val fragment = SliderItemFragment()
            val args = Bundle()
            args.putInt(ARG_KEY_POS, position)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SliderItemViewModel::class.java)
        viewModel.position = requireArguments().getInt(ARG_KEY_POS)
        binding.layoutBg.setBackgroundResource(viewModel.bgImage())
        binding.textView1.setText(viewModel.pageTitle())
        binding.textView2.setText(viewModel.pageText())
        binding.imageView.setImageResource(viewModel.pageImage())
        with(viewModel) {
            Log.d(
                "DBG",
                "onViewCreated:$position-> [${pageText()} ${pageImage()} ${pageTitle()} ${bgImage()}]"
            )
        }
    }
}