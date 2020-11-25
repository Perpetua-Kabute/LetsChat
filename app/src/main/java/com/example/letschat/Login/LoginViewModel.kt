package com.example.letschat.Login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.letschat.database.DeviceDatabaseDao

class LoginViewModel(
        val deviceDao: DeviceDatabaseDao,
        application: Application
): AndroidViewModel(application){
    //insert a user from edit text

    //on button click I want to check if device name is entered and if true insert device name into Device
//    fun insertUSer(deviceName: String){
//        deviceDao.insert(deviceName)
//    }
}

