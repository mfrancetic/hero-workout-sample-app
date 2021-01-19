package com.mfrancetic.heroworkoutsampleapp.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _changeThemeEvent = MutableLiveData<Boolean>()
    val changeThemeEvent: LiveData<Boolean>
        get() = _changeThemeEvent

    init {
        _changeThemeEvent.value = false
    }

    fun onLightThemeButtonClicked() {
        _changeThemeEvent.value = true
    }

    fun onDarkThemeButtonClicked() {
        _changeThemeEvent.value = false
    }

    fun onChangeThemeEventDone() {
        _changeThemeEvent.value = null
    }
}