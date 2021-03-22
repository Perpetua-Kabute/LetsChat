package com.example.letschat.ui.viewmodels


import com.example.letschat.repository.DeviceRepository
import android.app.Application
import androidx.lifecycle.*
import com.example.letschat.data.entities.Device
import com.example.letschat.data.dao.DeviceDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: DeviceRepository
): ViewModel(){


    suspend fun insertDevice(device: Device) {
        repository.insert(device)
    }

    val devices: LiveData<List<Device>> = repository.deviceList.asLiveData()

    suspend fun getDeviceName(deviceId: Long){
        repository.getDeviceName(deviceId)
    }
}

