package com.example.letschat.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.letschat.data.entities.Device
import kotlinx.coroutines.flow.Flow

@Dao
interface DeviceDatabaseDao {
    //insert a device name
    @Insert
    suspend fun insert(device: Device)

    //get device name
    @Query("SELECT device_name FROM device_table WHERE deviceId = :key")
    suspend fun get(key: Long): String

    @Query("SELECT * FROM device_table ")
    fun getDevice(): Flow<List<Device>>

}