package org.hiro.financeapp.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.hiro.financeapp.R
import org.hiro.financeapp.database.MyDatabase
import org.hiro.financeapp.databinding.FragmentSignUpBinding
import org.hiro.financeapp.model.User
import org.hiro.financeapp.ui.base.BaseFragment
import org.hiro.financeapp.util.HawkUtils
import org.hiro.financeapp.viewmodel.SignUpViewModel
import org.hiro.financeapp.viewmodel.factory.SignUpViewModelFactory

class SignUpFragment :
    BaseFragment<FragmentSignUpBinding>(FragmentSignUpBinding::inflate) {

    private lateinit var viewModel: SignUpViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            this, SignUpViewModelFactory(MyDatabase.getInstance(requireContext()).userDao)
        ).get(SignUpViewModel::class.java)
        initListeners()
    }


    private fun initListeners() {
        binding.signInBtn.setOnClickListener {
            findNavController().popBackStack()
            findNavController().navigate(R.id.fragmentSignIn)
        }

        binding.registerUserBtn.setOnClickListener {
            checkValidity()
        }
    }


    private fun createUser(user: User) {
        Log.d("DBG", "createUser: $user")
        if (viewModel.signUp(user)) {
            findNavController().popBackStack()
            findNavController().navigate(R.id.fragmentSignIn)
        }
    }

    private fun checkValidity() {
        with(binding) {
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val number = numeberEditText.text.toString()
            val rePassword = rePasswordEditText.text.toString()

            if (email.isNotBlank() && password.isNotBlank() && name.isNotBlank() &&
                rePassword.isNotBlank() && number.isNotBlank()
            ) {
                if (!checkEmail()) emailEditText.error = "Wrong formatted email"
                else if (password.length < 6)
                    passwordEditText.error = "Password length should be more than 6 characters"
                else if (rePassword != password)
                    rePasswordEditText.error = "Password is not compatible"
                else createUser(User(null, name, email, number, password))
            } else {
                showSnackBar("Every field must be filled up")
                val s = "This field must be filled"
                if (email.isBlank())
                    emailEditText.error = s
                if (password.isBlank())
                    passwordEditText.error = s
                if (number.isBlank())
                    passwordEditText.error = s
                if (name.isBlank())
                    nameEditText.error = s
                if (rePassword.isBlank())
                    rePasswordEditText.error = s
            }
        }

    }

    private fun checkEmail() =
        binding.emailEditText.text.toString()
            .matches(Regex("^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}\$"))

}