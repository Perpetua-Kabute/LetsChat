package com.example.letschat

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.HandlerCompat.postDelayed
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.letschat.Login.LoginFragment
import com.example.letschat.database.Device
import com.example.letschat.database.LetschatDatabase
import kotlinx.coroutines.*


class SplashFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onResume() {
        super.onResume()
        //check if shared preferences is null
        Handler().postDelayed({
            //if Device is empty, Navigate to login else navigate to Messages fragment
                             checkIfSignedIn()
        }, 2000)
        Log.i("SplashFragment", "onResume called")

    }
    fun checkIfSignedIn(){
        val application = requireNotNull(this.activity).application
        val fragmentJob = Job()
        val uiScope = CoroutineScope(Dispatchers.Main + fragmentJob)

        uiScope.launch(Dispatchers.IO) {
           val dataSource = LetschatDatabase.getInstance(application).deviceDatabaseDao
            val device =dataSource.getDevice()
            Log.i("Splash Fragment", "inserted ${device}")

            withContext(Dispatchers.Main) {
               if( device != null){
                   findNavController().navigate(R.id.action_splashFragment_to_messagesFragment2)
                }else{
                   findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
               }
            }
        }
    }

}