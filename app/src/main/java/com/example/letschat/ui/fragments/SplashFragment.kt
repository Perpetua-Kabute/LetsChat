package com.example.letschat.ui.fragments


import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.letschat.ui.viewmodels.LoginViewModel
import com.example.letschat.ui.viewmodelfactory.LoginViewModelFactory
import com.example.letschat.R
import com.example.letschat.data.LetschatDatabase
import com.example.letschat.repository.DeviceRepository
import kotlinx.coroutines.*


class SplashFragment : Fragment() {

    private lateinit var deviceRepository: DeviceRepository
    private lateinit var viewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val database =  LetschatDatabase.getInstance(requireContext())
        Log.i("RepositoryDb splash", "$database")
        deviceRepository =  DeviceRepository(database.deviceDatabaseDao())
        Log.i("Repositoryinstasplas", "${deviceRepository}")

       viewModel = ViewModelProvider(this, LoginViewModelFactory(deviceRepository)).get(LoginViewModel::class.java)

        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onResume() {
        super.onResume()
        Handler(Looper.getMainLooper()).postDelayed({

           checkIfSignedIn(viewModel)

        }, 2000)
        Log.i("SplashFragment", "onResume called")

    }
    private fun checkIfSignedIn(vm: LoginViewModel){

        lifecycleScope.launch {

            vm.devices.observe(viewLifecycleOwner, Observer {
                if(it.isNullOrEmpty()){
                    findNavController().navigate(R.id.action_splashFragment_to_signUpFragment)
                }else{
                    val myPrefs = activity?.getPreferences(Context.MODE_PRIVATE) ?: return@Observer
                    val userName = myPrefs.getString(getString(R.string.user_name), null)
                    if(userName == null){
                        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToProfileFragment(it[0].deviceName.toString()))
                    }else{
                        findNavController().navigate(
                                SplashFragmentDirections.actionSplashFragmentToMessagesFragment2(
                                        it[0].deviceName.toString()
                                )
                        )
                    }

                    Log.i("Splash Fragment", "inserted $it")
                }

            })

        }

    }

    override fun onDestroy() {
        super.onDestroy()

    }

}