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

    init {
        _changeToDarkThemeEvent.value = true
        _email.value = ""
        _password.value = ""
    }

    fun onLightThemeButtonClicked() {
        _changeToDarkThemeEvent.value = false
    }

    fun onDarkThemeButtonClicked() {
        _changeToDarkThemeEvent.value = true
    }

    fun onChangeThemeEventDone() {
        _changeToDarkThemeEvent.value = null
    }
}