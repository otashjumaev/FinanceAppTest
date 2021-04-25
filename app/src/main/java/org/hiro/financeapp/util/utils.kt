package org.hiro.financeapp.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun formatTime(time: Long?): String = SimpleDateFormat("dd MMMM yyyy").format(Date(time ?: 0))
