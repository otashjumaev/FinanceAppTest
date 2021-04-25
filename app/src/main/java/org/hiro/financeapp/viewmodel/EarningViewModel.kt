package org.hiro.financeapp.viewmodel

import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.core.graphics.toColorInt
import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import org.hiro.financeapp.databinding.ListItemPaymentBinding
import org.hiro.financeapp.model.Payment
import org.hiro.financeapp.util.formatTime
import java.util.*
import kotlin.math.abs
import kotlin.random.Random


class EarningViewModel : ViewModel() {
    val paymentData = mutableListOf<Payment>()
    fun initData() {
        repeat(10) {
            paymentData.add(
                Payment(
                    "Pay Earning $it",
                    Date(-946771200000L + (abs(Random.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000))).time
                )
            )
        }
    }

    fun paymentAdapterDelegate(itemClickedListener: (Payment) -> Unit) =
        adapterDelegateViewBinding<Payment, Payment, ListItemPaymentBinding>(
            { layoutInflater, root -> ListItemPaymentBinding.inflate(layoutInflater, root, false) }
        ) {
            binding.root.setOnClickListener {
                itemClickedListener(item)
            }
            bind {
                binding.headerText.text = item.name
                binding.text2.text = formatTime(item.date)
            }
        }

    fun getDataSet(drawable: Drawable?): LineData {
        val set1 = LineDataSet(
            (0..10).map {
                Entry(it.toFloat(), (15..25).random().toFloat())
            }, "DataSet1"
        )

        val set2 = LineDataSet(
            (0..10).map {
                Entry(it.toFloat(), (5..15).random().toFloat())
            }, "DataSet1"
        )
        initSet(set1, drawable)
        initSet(set2, drawable)

        val data = LineData(set1, set2)

        data.setDrawValues(false)
        data.setValueTextSize(9f)

        return LineData(set1, set2)
    }

    private fun initSet(set: LineDataSet, drawable: Drawable?) {
//        set.color = ColorTemplate.rgb("FF018786")
        set.lineWidth = 2f
        set.setDrawCircles(false)
        set.mode = LineDataSet.Mode.CUBIC_BEZIER
        set.cubicIntensity = 0.2f
        set.setDrawFilled(true)
        set.setGradientColor(Color.RED, Color.WHITE)
        set.setDrawCircles(false)
        set.fillFormatter = IFillFormatter { _, _ ->
            0F
        }
        set.lineWidth = 1.8f
        set.circleRadius = 4f
        set.setCircleColor(Color.WHITE)
        set.highLightColor = Color.BLUE
        set.color = "#4044FC".toColorInt()
        set.fillDrawable = drawable
//        set.fillColor = Color.WHITE
//        set.fillAlpha = 37
        set.setDrawHorizontalHighlightIndicator(false)
    }
}
