package com.example.letschat.ui.viewmodelfactory


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.letschat.repository.DeviceRepository
import com.example.letschat.ui.viewmodels.LoginViewModel

class LoginViewModelFactory(
    private val repository: DeviceRepository,
    ): ViewModelProvider.Factory{

    @Suppress("Unchecked cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
