package com.example.letschat.ui.fragments


import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.letschat.R
import com.example.letschat.ui.viewmodels.LoginViewModel
import com.example.letschat.ui.viewmodelfactory.LoginViewModelFactory
import com.example.letschat.data.LetschatDatabase

import com.example.letschat.data.entities.Device
import com.example.letschat.data.dao.DeviceDatabaseDao
import com.example.letschat.databinding.FragmentSignupBinding
import com.example.letschat.repository.DeviceRepository
import kotlinx.coroutines.*


class SignUpFragment : Fragment() {
    private lateinit var dataSource: DeviceDatabaseDao
    private  var _binding: FragmentSignupBinding?= null
    private val binding get() = _binding!!
    private val deviceId = 0L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        //get the application

        binding.lifecycleOwner = this

        binding.signIn.setOnClickListener { view ->
            view.hideKeyboard()
            val userName = binding.userName.text.toString()
            val phoneNumber = binding.phoneNumber.text.toString()
            if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(phoneNumber)){

                if(TextUtils.isEmpty(phoneNumber)){
                    val noPhoneNumberErr = binding.phoneNumberEntry
                    noPhoneNumberErr.error = "Phone number missing"
                    noPhoneNumberErr.requestFocus()
                }
            }else{
                findNavController().navigate(
                    SignUpFragmentDirections.actionSignUpFragmentToMessagesFragment2(
                        userName
                    )
                )

            }
        }

        return binding.root
    }

    fun authWithPhone(){

    }
    fun View.hideKeyboard() {
        val imm =  getSystemService(context,
                InputMethodManager::class.java) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}