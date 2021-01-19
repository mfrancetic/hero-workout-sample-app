package com.mfrancetic.heroworkoutsampleapp.screens.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.mfrancetic.heroworkoutsampleapp.R
import com.mfrancetic.heroworkoutsampleapp.databinding.LoginFragmentBinding
import com.mfrancetic.heroworkoutsampleapp.screens.utils.*

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: LoginFragmentBinding
    private lateinit var fragmentContext: Context

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
        fragmentContext = binding.darkThemeButton.context
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.changeThemeEvent.observe(viewLifecycleOwner, { changeThemeEvent ->
            if (changeThemeEvent != null) {
                changeTheme(changeThemeEvent)
            }
        })

        viewModel.email.observe(viewLifecycleOwner, { email ->
            viewModel.setEmailValid(isEmailValid(email))
        })

        viewModel.password.observe(viewLifecycleOwner, { password ->
            viewModel.setPasswordValid(validatePassword(password))
        })

        viewModel.loginButtonClickedEvent.observe(viewLifecycleOwner, { loginButtonClickedEvent ->
            if (loginButtonClickedEvent) {
                if (viewModel.isEmailValid.value == true && viewModel.isPasswordValid.value == true) {
                    viewModel.setDisplayLoginDialogEvent()
                } else {
                    viewModel.setDisplaySnackbarEvent()
                }
                viewModel.onLoginButtonClickedEventDone()
            }
        })

        viewModel.displaySnackbarEvent.observe(viewLifecycleOwner, { displaySnackbarEvent ->
            if (displaySnackbarEvent) {
                hideKeyboard()
                displaySnackbarFieldsInvalid()
                viewModel.onDisplaySnackbarEventDone()
            }
        })

        viewModel.displayLoginDialogEvent.observe(viewLifecycleOwner, { displayLoginDialogEvent ->
            if (displayLoginDialogEvent) {
                hideKeyboard()
                displayLoginDialog()
                viewModel.onDisplayLoginDialogEventDone()
            }
        })
    }

    private fun displaySnackbarFieldsInvalid() {
        Snackbar.make(
            requireView(),
            getString(R.string.all_fields_must_be_valid),
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun hideKeyboard() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

    private fun displayLoginDialog() {
        val builder = AlertDialog.Builder(requireActivity(), R.style.Theme_AppCompat_Dialog)
            .setMessage(getString(R.string.you_have_successfully_logged_in))
        builder.create().show()
    }

    private fun isEmailValid(email: String): Boolean {
        val isEmailValid = isEmailAddressValid(email)
        if (isEmailValid) {
            binding.emailLayout.isErrorEnabled = !isEmailValid
        } else {
            binding.emailEditText.error = getString(R.string.email_invalid)
        }
        return isEmailValid
    }

    private fun validatePassword(password: String): Boolean {
        val passwordError: String = getPasswordValidationError(password)
        val passwordErrorMessage: String
        val isPasswordValid: Boolean

        if (passwordError.isEmpty()) {
            isPasswordValid = true
            binding.passwordLayout.isErrorEnabled = !isPasswordValid
        } else {
            isPasswordValid = false
            passwordErrorMessage = when (passwordError) {
                PASSWORD_TOO_SHORT -> getString(R.string.password_too_short)
                PASSWORD_NUMBER_MISSING -> getString(R.string.password_number_missing)
                PASSWORD_CAPITAL_LETTER_MISSING -> getString(R.string.capital_letter_missing)
                PASSWORD_SPECIAL_CHARACTER_MISSING -> getString(R.string.special_character_missing)
                else -> getString(R.string.password_invalid)
            }
            binding.passwordEditText.error = passwordErrorMessage
        }
        return isPasswordValid
    }

    private fun changeTheme(changeThemeEvent: Boolean) {
        when (changeThemeEvent) {
            true -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.loginConstraintLayout.background = ContextCompat.getDrawable(
                    fragmentContext,
                    R.drawable.login_background
                )
            }
            false -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.loginConstraintLayout.background = ContextCompat.getDrawable(
                    fragmentContext,
                    R.color.white
                )
            }
        }
    }
}