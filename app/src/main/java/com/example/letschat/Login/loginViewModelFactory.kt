package com.example.letschat.Login

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.letschat.database.DeviceDatabaseDao

class LoginViewModelFactory(
        private val dataSource: DeviceDatabaseDao,
        private val application: Application): ViewModelProvider.Factory{

    @Suppress("Unchecked cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
