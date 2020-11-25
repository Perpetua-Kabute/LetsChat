package com.example.letschat.Login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.letschat.R
import com.example.letschat.database.Device
import com.example.letschat.database.LetschatDatabase
import com.example.letschat.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login,container, false)
//        //get the application
//        val application = requireNotNull(this.activity).application
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
            if(deviceName.isEmpty()){
                Log.i("LoginFragment", "Device name empty")

            }
            val userDevice=  Device(deviceName = deviceName)


        }

        return binding.root
    }

    //insert a user from edit text

    //on button click I want to check if device name is entered and if true insert device name into Device


}