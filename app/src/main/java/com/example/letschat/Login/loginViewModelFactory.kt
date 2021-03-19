package com.example.letschat.Login


import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.letschat.data.dao.DeviceDatabaseDao
import com.example.letschat.repository.DeviceRepository

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
