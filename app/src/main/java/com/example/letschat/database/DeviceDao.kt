package com.example.letschat.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DeviceDao {
    //insert a device name
    @Insert
    fun insert(device: Device)

    //get device name
    @Query("SELECT device_name FROM device_table WHERE deviceId = :key")
    fun get(key: Long)

}