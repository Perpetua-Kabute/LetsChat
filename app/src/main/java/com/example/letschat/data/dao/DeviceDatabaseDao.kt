package com.example.letschat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.letschat.data.entities.Device

@Dao
interface DeviceDatabaseDao {
    //insert a device name
    @Insert
    fun insert(device: Device)

    //get device name
    @Query("SELECT device_name FROM device_table WHERE deviceId = :key")
    fun get(key: Long): String

    @Query("SELECT * FROM device_table ")
    fun getDevice(): List<Device>

}