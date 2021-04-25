package org.hiro.financeapp.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import org.hiro.financeapp.databinding.ListItemCategoryBinding
import org.hiro.financeapp.databinding.ListItemTransactionBinding
import org.hiro.financeapp.model.Category
import org.hiro.financeapp.model.Transaction
import kotlin.random.Random

class HomeViewModel : ViewModel() {
    val catData = mutableListOf<Category>()
    val transactionData = mutableListOf<Transaction>()


    fun initData() {
        repeat(20) {
            catData.add(Category("Category $it"))
            transactionData.add(
                Transaction(
                    "Hailey Nolan $it",
                    Random.nextInt(100),
                    Random.nextInt(1000, 10000)
                )
            )
        }
    }

    fun catAdapterDelegate(itemClickListener: (Category) -> Unit) =
        adapterDelegateViewBinding<Category, Category, ListItemCategoryBinding>(
            { layoutInflater, root -> ListItemCategoryBinding.inflate(layoutInflater, root, false) }
        ) {
            binding.root.setOnClickListener {
                itemClickListener(item)
            }
            bind {
                binding.categoryTitle.text = item.name
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
}