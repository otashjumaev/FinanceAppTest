package org.hiro.financeapp.viewmodel

import androidx.lifecycle.ViewModel
import org.hiro.financeapp.R

class SliderItemViewModel : ViewModel() {

    var position: Int = 0

    fun pageTitle() =
        intArrayOf(R.string.intro_title_1, R.string.intro_title_2, R.string.intro_title_3)[position]

    fun pageText() = R.string.intro_sub_text

    fun pageImage() = intArrayOf(
        R.drawable.ic_undraw_investing_7u74,
        R.drawable.ic_undraw_vault_9cmw,
        R.drawable.ic_undraw_transfer_money_rywa
    )[position]

    fun bgImage() = intArrayOf(
        R.drawable.bg_intro_screen,
        R.drawable.bg_intro_screen_2,
        R.drawable.bg_intro_screen_3
    )[position]

}
