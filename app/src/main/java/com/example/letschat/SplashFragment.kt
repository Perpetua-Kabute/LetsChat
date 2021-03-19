package com.example.letschat


import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.letschat.Login.LoginViewModel
import com.example.letschat.Login.LoginViewModelFactory
import com.example.letschat.data.LetschatDatabase
import com.example.letschat.data.entities.Device
import com.example.letschat.repository.DeviceRepository
import kotlinx.coroutines.*


class SplashFragment : Fragment() {
    var iFHasNotSignedUP: Boolean = false
    private lateinit var deviceRepository: DeviceRepository
    val viewModel : LoginViewModel by viewModels {
        LoginViewModelFactory(deviceRepository)
    }

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

        Log.i("Repository splash", "$deviceRepository")

        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({
           checkIfSignedIn(viewModel)

        }, 2000)
        Log.i("SplashFragment", "onResume called")

    }
    private fun checkIfSignedIn(vm: LoginViewModel){

        lifecycleScope.launch {

            vm.devices.observe(viewLifecycleOwner, Observer {
                if(it.isNullOrEmpty()){
                    findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                }else{
                    findNavController().navigate(R.id.action_splashFragment_to_messagesFragment2)
                    Log.i("Splash Fragment", "inserted $it")
                }


            })


        }


    }

    override fun onDestroy() {
        super.onDestroy()

    }

}