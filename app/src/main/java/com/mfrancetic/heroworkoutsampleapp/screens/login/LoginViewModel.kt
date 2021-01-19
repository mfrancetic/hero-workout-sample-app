package com.mfrancetic.heroworkoutsampleapp.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _changeToDarkThemeEvent = MutableLiveData<Boolean>()
    val changeThemeEvent: LiveData<Boolean>
        get() = _changeToDarkThemeEvent

    private val _email = MutableLiveData<String>()
    val email: MutableLiveData<String>
        get() = _email

    private val _password = MutableLiveData<String>()
    val password: MutableLiveData<String>
        get() = _password

    private val _isEmailValid = MutableLiveData<Boolean>()
    val isEmailValid: LiveData<Boolean>
        get() = _isEmailValid

    private val _isPasswordValid = MutableLiveData<Boolean>()
    val isPasswordValid: LiveData<Boolean>
        get() = _isPasswordValid

    private val _loginButtonClickedEvent = MutableLiveData<Boolean>()
    val loginButtonClickedEvent: LiveData<Boolean>
        get() = _loginButtonClickedEvent

    private val _displayLoginDialogEvent = MutableLiveData<Boolean>()
    val displayLoginDialogEvent: LiveData<Boolean>
        get() = _displayLoginDialogEvent

    private val _displaySnackbarEvent = MutableLiveData<Boolean>()
    val displaySnackbarEvent: LiveData<Boolean>
        get() = _displaySnackbarEvent

    init {
        _changeToDarkThemeEvent.value = true
        _email.value = ""
        _password.value = ""
    }

    fun setEmailValid(isEmailValid: Boolean) {
        _isEmailValid.value = isEmailValid
    }

    fun setPasswordValid(isPasswordValid: Boolean) {
        _isPasswordValid.value = isPasswordValid
    }

    fun onLightThemeButtonClicked() {
        _changeToDarkThemeEvent.value = false
    }

    fun onDarkThemeButtonClicked() {
        _changeToDarkThemeEvent.value = true
    }

    fun onLoginButtonClicked() {
        _loginButtonClickedEvent.value = true
    }

    fun onLoginButtonClickedEventDone() {
        _loginButtonClickedEvent.value = false
    }

    fun setDisplayLoginDialogEvent() {
        _displayLoginDialogEvent.value = true
    }

    fun onDisplayLoginDialogEventDone() {
        _displayLoginDialogEvent.value = false
    }

    fun setDisplaySnackbarEvent() {
        _displaySnackbarEvent.value = true
    }

    fun onDisplaySnackbarEventDone() {
        _displaySnackbarEvent.value = false
    }
}