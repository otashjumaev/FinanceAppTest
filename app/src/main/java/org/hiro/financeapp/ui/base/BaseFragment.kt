package org.hiro.financeapp.ui.base


import android.content.Context
import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import org.hiro.financeapp.MainActivity

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: Inflate<VB>
) : Fragment() {
    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    var detached = true

    fun pressBack() {
        activity?.let { act ->
            if (act is MainActivity) {
                act.onBackPressed()
            }
        }
    }

    fun showSnackBar(message: String?) {
        Snackbar.make(requireView(), message ?: "", Snackbar.LENGTH_SHORT).show()
    }

    fun closeKeyboard() {
        val inputManager =
            context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        var view = activity?.currentFocus
        if (view == null && activity != null) {
            view = View(activity)
        }
        val binder = view?.windowToken
        inputManager?.hideSoftInputFromWindow(binder, 0)
    }

    override fun onDetach() {
        super.onDetach()
        detached = true
        closeKeyboard()
    }

}