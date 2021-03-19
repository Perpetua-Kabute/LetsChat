package com.example.letschat.repository
import androidx.annotation.WorkerThread
import com.example.letschat.data.dao.DeviceDatabaseDao
import com.example.letschat.data.entities.Device

class DeviceRepository (private val deviceDao: DeviceDatabaseDao){


    suspend fun insert(device: Device){
        deviceDao.insert(device)
    }

    val deviceList = deviceDao.getDevice()

    suspend fun getDeviceName(primaryKey: Long): String{
        return deviceDao.get(primaryKey)
    }
}