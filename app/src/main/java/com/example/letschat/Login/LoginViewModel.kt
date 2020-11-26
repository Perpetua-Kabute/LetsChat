package com.example.letschat.Login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.letschat.database.Device
import com.example.letschat.database.DeviceDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class LoginViewModel(
        val deviceDao: DeviceDatabaseDao,
        application: Application
): AndroidViewModel(application){
    //insert a user from edit text

    //on button click I want to check if device name is entered and if true insert device name into Device
//    fun insertUSer(deviceName: String){
//        deviceDao.insert(deviceName)
//    }
    //create viewmodeljob and cancel couroutines in oncleared
    private var viewModelJob = Job()
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
    //define scope for the coroutine. determines the thread the coroutine should run on and the job
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var device = MutableLiveData<Device?>()



}

