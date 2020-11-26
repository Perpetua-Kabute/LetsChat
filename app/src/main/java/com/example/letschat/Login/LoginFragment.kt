package com.example.letschat.Login

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.letschat.MainActivity
import com.example.letschat.R
import com.example.letschat.database.Device
import com.example.letschat.database.DeviceDatabaseDao
import com.example.letschat.database.LetschatDatabase
import com.example.letschat.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    private lateinit var databaseDao: DeviceDatabaseDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login,container, false)
//        //get the application
        val application = requireNotNull(this.activity).application
//
//        //reference to datasource
//        val dataSource = LetschatDatabase.getInstance(application).deviceDatabaseDao
//
//        //get the ViewModelFactory
//        val viewModelFactory = LoginViewModelFactory(dataSource, application)
//
//        //get viewmodel
//        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
//
//        binding.setLifecycleOwner(thiss)
        val deviceName = binding.deviceName.text.toString()
        binding.signIn.setOnClickListener {
            if(TextUtils.isEmpty(deviceName)){
                Log.i("LoginFragment", "Device name empty")
                Toast.makeText(activity, "Enter the name of your device", Toast.LENGTH_SHORT).show()
            }
            val userDevice= Device(deviceName = deviceName)
            databaseDao.insert(userDevice)

        }

        return binding.root
    }

    //insert a user from edit text

    //on button click I want to check if device name is entered and if true insert device name into Device


}