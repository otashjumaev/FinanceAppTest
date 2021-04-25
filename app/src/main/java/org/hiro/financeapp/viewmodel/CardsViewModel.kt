package org.hiro.financeapp.viewmodel

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.lifecycle.ViewModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import org.hiro.financeapp.R
import org.hiro.financeapp.databinding.*
import org.hiro.financeapp.model.*
import org.hiro.financeapp.util.formatTime
import java.util.*
import kotlin.math.abs
import kotlin.random.Random

class CardsViewModel : ViewModel() {
    val cardsData = mutableListOf<Card>()
    val contactsData = mutableListOf<Contact>()
    val transactionData = mutableListOf<Transaction>()
    fun initData() {
        repeat(20) {
            cardsData.add(
                Card("PEEELY AGENCY", "•••• •••• •••• 0113", "01/24")
            )
            contactsData.add(Contact("Elvira Spillane $it"))
            transactionData.add(Transaction("Category $it"))
        }
    }

    @SuppressLint("SetTextI18n")
    fun transactionAdapterDelegate(itemClickListener: (Transaction) -> Unit) =
        adapterDelegateViewBinding<Transaction, Transaction, ListItemTransactionBinding>(
            { layoutInflater, root ->
                ListItemTransactionBinding.inflate(layoutInflater, root, false)
            }) {
            binding.root.setOnClickListener {
                itemClickListener(item)
            }
            bind {
                binding.headerText.text = item.name
                binding.subText.text = "${item.number} transactions"
                binding.sumText.text = "${item.sum}"
            }
        }

    fun cardAdapterDelegate(itemClickedListener: (Card) -> Unit) =
        adapterDelegateViewBinding<Card, Card, ListItemCardBinding>(
            { layoutInflater, root -> ListItemCardBinding.inflate(layoutInflater, root, false) }
        ) {
            binding.root.setOnClickListener {
                itemClickedListener(item)
            }
            bind {
                val b = Random.nextInt() % 2 == 0
                val color = if (!b) Color.BLACK else Color.WHITE
                binding.root.setBackgroundResource(if (b) R.drawable.bg_card else R.drawable.ic_card_bg)
                binding.cardHolder.text = item.cardHolder
                binding.cardNumber.text = item.cardNumber
                binding.cardexpDate.text = item.cardExpDate
                binding.cardHolder.setTextColor(color)
                binding.cardNumber.setTextColor(color)
                binding.cardexpDate.setTextColor(color)
            }
        }

    fun contactAdapterDelegate(itemClickedListener: (Contact) -> Unit) =
        adapterDelegateViewBinding<Contact, Contact, ListItemContactBinding>(
            { layoutInflater, root -> ListItemContactBinding.inflate(layoutInflater, root, false) }
        ) {
            binding.root.setOnClickListener {
                itemClickedListener(item)
            }
            bind {
                binding.categoryTitle.text = item.name
            }
        }

}
