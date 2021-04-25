package org.hiro.financeapp.ui

import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import org.hiro.financeapp.databinding.FragmentHomeBinding
import org.hiro.financeapp.model.Category
import org.hiro.financeapp.model.Transaction
import org.hiro.financeapp.model.UserMini
import org.hiro.financeapp.ui.base.BaseFragment
import org.hiro.financeapp.util.HawkUtils
import org.hiro.financeapp.util.getData
import org.hiro.financeapp.viewmodel.HomeViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private lateinit var viewModel: HomeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val user = HawkUtils.user
        binding.nameText.text = user?.name ?: "NULL"
        initCatList()
        initTransactionList()
    }

    private fun initCatList() {
        viewModel.initData()
        val adapter = ListDelegationAdapter(viewModel.catAdapterDelegate {
            showSnackBar(it.name)
        })
        adapter.items = viewModel.catData
        binding.list1.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.HORIZONTAL, false)
        binding.list1.adapter = adapter
    }

    private fun initTransactionList() {
        val adapter = ListDelegationAdapter(viewModel.transactionAdapterDelegate {
            showSnackBar(it.name)
        })
        adapter.items = viewModel.transactionData
        binding.listTransact.layoutManager = LinearLayoutManager(requireContext())
        binding.listTransact.adapter = adapter
    }
}