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

        val database =  LetschatDatabase.getInstance(requireContext())
        Log.i("RepositoryDatabase", "$database")
        val deviceRepository =  DeviceRepository(database.deviceDatabaseDao())
        Log.i("Repositoryinsta", "${deviceRepository}")

        Log.i("Repository", "$deviceRepository")

        //get viewmodel
        val viewModel : LoginViewModel by viewModels {
            LoginViewModelFactory(deviceRepository)
        }

        binding.lifecycleOwner = this

        binding.signIn.setOnClickListener { view ->
            view.hideKeyboard()
            val deviceName = binding.deviceName.text.toString()
            if(TextUtils.isEmpty(deviceName)){
                val deviceErr = binding.deviceErr
                deviceErr.error = "Device name missing"
                deviceErr.requestFocus()
            }else{
                val device = Device(deviceName = deviceName)
                Log.i("Device unInserted = " ,"$device")
                lifecycleScope.launch {
                    viewModel.insertDevice(device)
                }


                viewModel.devices
                Log.i("Devices = " ,"${viewModel.devices.value}")
                viewModel.devices.observe(viewLifecycleOwner, Observer {
                    if(it.isNullOrEmpty()){
                        Toast.makeText(context, "Could not sign up", Toast.LENGTH_SHORT).show()
                    }else{
                        Log.i("Devices it= " ,"$it")
                        val myPrefs = activity?.getPreferences(Context.MODE_PRIVATE) ?: return@Observer
                        val userName = myPrefs.getString(getString(R.string.user_name), null)
                        if(userName == null){
                            findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToProfileFragment(it[0].deviceName.toString()))
                        }else {
                            findNavController().navigate(
                                    SignUpFragmentDirections.actionSignUpFragmentToMessagesFragment2(
                                            it[0].deviceName.toString()
                                    )
                            )
                        }
                    }
                })

            }
        }

        return binding.root
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