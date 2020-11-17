package com.example.letschat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.letschat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main)
        Log.i("Main", "Activity created")
    }
}