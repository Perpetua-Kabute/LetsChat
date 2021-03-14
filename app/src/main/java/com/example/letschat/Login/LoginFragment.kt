package com.example.letschat.Login

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
import androidx.navigation.Navigation
import com.example.letschat.R
import com.example.letschat.data.entities.Device
import com.example.letschat.data.dao.DeviceDatabaseDao
import com.example.letschat.data.LetschatDatabase
import com.example.letschat.databinding.FragmentLoginBinding
import kotlinx.coroutines.*


class LoginFragment : Fragment() {
    private lateinit var dataSource: DeviceDatabaseDao
    private  var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val LoginJob = Job()
    private val deviceId = 0L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        //get the application
        val application = requireNotNull(this.activity).application

//        //reference to datasource
        dataSource = LetschatDatabase.getInstance(application).deviceDatabaseDao
//
//        //get the ViewModelFactory
//        val viewModelFactory = LoginViewModelFactory(dataSource, application)
//
//        //get viewmodel
//        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
//
//        binding.setLifecycleOwner(this)

        binding.signIn.setOnClickListener {
            insertUser(it)
        }

        return binding.root
    }
    fun View.hideKeyboard() {
        val imm =  getSystemService(context,
                InputMethodManager::class.java) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    //insert a user from edit text
    //on button click I want to check if device name is entered and if true insert device name into Device

    private fun insertUser(view: View){
        val deviceName = binding.deviceName.text.toString()
        if(TextUtils.isEmpty(deviceName)){
            Log.i("LoginFragment", "Device name empty")
            Toast.makeText(activity, "Enter the name of your device", Toast.LENGTH_SHORT).show()
        }else {

            val uiScope = CoroutineScope(Dispatchers.Main + LoginJob)

            uiScope.launch(Dispatchers.IO) {
                val userDevice = Device(deviceName = deviceName)
                dataSource.insert(userDevice)
                Log.i("Login", "called Login")
                val device = dataSource.getDevice()

                Log.i("Login Fragment", "inserted ${device}")



                withContext(Dispatchers.Main) {
                    view.hideKeyboard()
                    Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_messagesFragment2)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        LoginJob.cancel()
        _binding = null
    }



}