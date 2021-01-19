package com.mfrancetic.heroworkoutsampleapp.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mfrancetic.heroworkoutsampleapp.R
import com.mfrancetic.heroworkoutsampleapp.databinding.LoginFragmentBinding
import com.mfrancetic.heroworkoutsampleapp.screens.utils.*

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.viewModel = viewModel
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.changeThemeEvent.observe(viewLifecycleOwner, { changeThemeEvent ->
            if (changeThemeEvent != null) {
                changeTheme(changeThemeEvent)
                viewModel.onChangeThemeEventDone()
            }
        })

        viewModel.email.observe(viewLifecycleOwner, { email ->
            validateEmail(email)
        })

        viewModel.password.observe(viewLifecycleOwner, { password ->
            validatePassword(password)
        })
    }

    private fun validateEmail(email: String) {
        val isEmailValid = isEmailValid(email)
        if (isEmailValid) {
            binding.emailLayout.isErrorEnabled = !isEmailValid
        } else {
            binding.emailEditText.error = getString(R.string.email_invalid)
        }
    }

    private fun validatePassword(password: String) {
        val passwordError: String = getPasswordValidationError(password)
        val passwordErrorMessage: String
        val isPasswordValid = false
        if (passwordError.isEmpty()) {
            binding.passwordLayout.isErrorEnabled = isPasswordValid
        } else {
            passwordErrorMessage = when (passwordError) {
                PASSWORD_TOO_SHORT -> getString(R.string.password_too_short)
                PASSWORD_NUMBER_MISSING -> getString(R.string.password_number_missing)
                PASSWORD_CAPITAL_LETTER_MISSING -> getString(R.string.capital_letter_missing)
                PASSWORD_SPECIAL_CHARACTER_MISSING -> getString(R.string.special_character_missing)
                else -> getString(R.string.password_invalid)
            }
            binding.passwordEditText.error = passwordErrorMessage
        }
    }

    private fun changeTheme(changeThemeEvent: Boolean) {
        when (changeThemeEvent) {
            true -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            false -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}