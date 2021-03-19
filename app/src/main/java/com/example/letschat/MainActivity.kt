package com.example.letschat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.databinding.DataBindingUtil
import com.example.letschat.data.LetschatDatabase
import com.example.letschat.databinding.ActivityMainBinding
import com.example.letschat.repository.DeviceRepository

class MainActivity : AppCompatActivity() {
    var deviceRepository: DeviceRepository? = null
    var database: LetschatDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main)
        Log.i("Main", "Activity created")
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)

        database =  LetschatDatabase.getInstance(this)
        Log.i("RepositoryDatabase", "$database")
        deviceRepository =  DeviceRepository(database!!.deviceDatabaseDao())
        Log.i("Repositoryinsta", "${deviceRepository}")

    }
}