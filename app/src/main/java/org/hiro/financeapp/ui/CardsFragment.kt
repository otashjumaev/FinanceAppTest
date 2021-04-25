package org.hiro.financeapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import org.hiro.financeapp.databinding.FragmentCardsBinding
import org.hiro.financeapp.ui.base.BaseFragment
import org.hiro.financeapp.viewmodel.CardsViewModel

class CardsFragment : BaseFragment<FragmentCardsBinding>(FragmentCardsBinding::inflate) {

    private lateinit var viewModel: CardsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CardsViewModel::class.java)
        initLists()
    }

    private fun initLists() {
        viewModel.initData()
        val cardAdapter = ListDelegationAdapter(viewModel.cardAdapterDelegate {
            showSnackBar(it.cardHolder)
        })
        cardAdapter.items = viewModel.cardsData
        binding.listCards.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.listCards.adapter = cardAdapter
        viewModel.initData()

        val contactAdapter = ListDelegationAdapter(viewModel.contactAdapterDelegate {
            showSnackBar(it.name)
        })
        contactAdapter.items = viewModel.contactsData
        binding.listContacts.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.listContacts.adapter = contactAdapter

        val transactAdapter = ListDelegationAdapter(viewModel.transactionAdapterDelegate {
            showSnackBar(it.name)
        })
        transactAdapter.items = viewModel.transactionData
        binding.listTransact.layoutManager =
            LinearLayoutManager(requireContext())
        binding.listTransact.adapter = transactAdapter
    }
}