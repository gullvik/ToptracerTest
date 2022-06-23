package com.example.tracercodetest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    private val _username = MutableLiveData<String>()
    val username : LiveData<String> = _username

    fun loginUser(name: String) {
        _username.postValue(name)
    }
}