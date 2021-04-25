package org.hiro.financeapp.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColorInt
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.LimitLine.LimitLabelPosition
import com.github.mikephil.charting.components.YAxis
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import org.hiro.financeapp.R
import org.hiro.financeapp.databinding.FragmentEarningBinding
import org.hiro.financeapp.ui.base.BaseFragment
import org.hiro.financeapp.viewmodel.EarningViewModel


class EarningsFragment : BaseFragment<FragmentEarningBinding>(FragmentEarningBinding::inflate) {
    private lateinit var viewModel: EarningViewModel
    private lateinit var chart: LineChart

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(EarningViewModel::class.java)
        chart = binding.lineGraph
        initGraph()
        updateChart()
        initList()
    }

    private fun initGraph1() {

    }

    private fun initList() {
        viewModel.initData()
        val adapter = ListDelegationAdapter(viewModel.paymentAdapterDelegate {
            showSnackBar(it.name)
        })
        adapter.items = viewModel.paymentData
        binding.listPay.layoutManager = LinearLayoutManager(requireContext())
        binding.listPay.adapter = adapter
    }

    private fun initGraph() {
        chart.setTouchEnabled(true)
        chart.isDragEnabled = true
        chart.setPinchZoom(false)
        chart.description.isEnabled = false
        chart.legend.isEnabled = false
        chart.setViewPortOffsets(0f, 0f, 0f, 0f)
//        chart.setBackgroundColor(Color.rgb(104, 241, 175))

//        chart.setDrawGridBackground(false)

        chart.xAxis.axisLineColor = "#F2F2F2".toColorInt()
        chart.xAxis.setDrawGridLines(false)
        val limitLine = LimitLine(5f)
        limitLine.lineWidth = 1f
        limitLine.lineColor = "#77869E".toColorInt()
        limitLine.labelPosition = LimitLabelPosition.RIGHT_BOTTOM
        chart.xAxis.addLimitLine(limitLine)

        val y = chart.axisLeft
        chart.axisRight.isEnabled = false
        y.enableGridDashedLine(10f, 10f, 0f)
        y.axisMaximum = 30f;
        y.axisMinimum = 0f;
        y.axisLineColor = "#F2F2F2".toColorInt()

        chart.invalidate()
    }

    private fun updateChart() {
        chart.data = viewModel.getDataSet(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.fade_blue
            )
        )
        if (chart.data != null && chart.data.dataSetCount > 0) {
            chart.data.notifyDataChanged()
            chart.notifyDataSetChanged()
        } else {
            chart.data.setValueTextColor(Color.BLUE)
            chart.invalidate()
        }
        chart.animateX(500)
    }
}