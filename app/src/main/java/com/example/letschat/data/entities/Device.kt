package com.example.letschat.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "device_table")

data class Device(
        @PrimaryKey(autoGenerate = true)
        val deviceId: Int = 0,

        @ColumnInfo(name = "device_name")
        val deviceName: String?

)