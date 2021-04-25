package org.hiro.financeapp.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.hiro.financeapp.R
import org.hiro.financeapp.database.MyDatabase
import org.hiro.financeapp.databinding.FragmentSignInBinding
import org.hiro.financeapp.model.UserMini
import org.hiro.financeapp.ui.base.BaseFragment
import org.hiro.financeapp.util.HawkUtils
import org.hiro.financeapp.util.putData
import org.hiro.financeapp.viewmodel.SignInViewModelModel
import org.hiro.financeapp.viewmodel.factory.SignInViewModelModelFactory

class SignInFragment : BaseFragment<FragmentSignInBinding>(FragmentSignInBinding::inflate) {

    private lateinit var viewModel: SignInViewModelModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            this, SignInViewModelModelFactory(MyDatabase.getInstance(requireContext()).userDao)
        ).get(SignInViewModelModel::class.java)
        initListeners()
    }

    private fun initListeners() {
        binding.enterBtn.setOnClickListener {
            checkValidity()
        }

        binding.registrationBtn.setOnClickListener {
            findNavController().navigate(R.id.fragmentSignUp)
        }
    }

    private fun checkValidity() {
        val email = binding.loginEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        if (email.isNotBlank() && password.isNotBlank()) {
            if (!checkEmail()) {
                binding.loginEditText.error = "Wrong formatted email"
                return
            }
            if (password.length < 6) {
                binding.passwordEditText.error = "Password length should be more than 6 characters"
                return
            }
            signInUser(email, password)
        } else {
            showSnackBar("Email or Password can not be blank")
            if (email.isBlank())
                binding.loginEditText.error = "Email must be filled"
            if (password.isBlank())
                binding.passwordEditText.error = "Password must be filled"
        }
    }

    private fun checkEmail() =
        binding.loginEditText.text.toString()
            .matches(Regex("^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}\$"))

    private fun signInUser(email: String, password: String) {
        if (viewModel.checkUser(email, password)) {
            HawkUtils.userLoggedIn = true
            HawkUtils.user = viewModel.user?.let { UserMini(it.name, it.email, it.phoneNumber) }
            findNavController().popBackStack()
            findNavController().navigate(R.id.mainFragment)
        } else {
            binding.loginEditText.error = "Email or Password is not correct"
            binding.passwordEditText.error = "Email or Password is not correct"
        }
    }

}