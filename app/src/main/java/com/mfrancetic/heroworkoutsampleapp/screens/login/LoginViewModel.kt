package com.mfrancetic.heroworkoutsampleapp.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _changeToDarkThemeEvent = MutableLiveData<Boolean>()
    val changeThemeEvent: LiveData<Boolean>
        get() = _changeToDarkThemeEvent

    init {
        _changeToDarkThemeEvent.value = true
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