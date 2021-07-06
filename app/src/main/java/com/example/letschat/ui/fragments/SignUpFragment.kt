package com.example.letschat.ui.fragments


import android.app.Activity
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
import com.firebase.ui.auth.AuthUI
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit


class SignUpFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var dataSource: DeviceDatabaseDao
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private  var _binding: FragmentSignupBinding?= null
    private val binding get() = _binding!!
    private val deviceId = 0L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _binding = FragmentSignupBinding.inflate(inflater, container, false)

        auth = FirebaseAuth.getInstance()

        val isUserSignedIn = auth.currentUser != null

        binding.lifecycleOwner = this


        binding.signIn.setOnClickListener { view ->
            view.hideKeyboard()
            val phoneNumber = binding.phoneNumber.text.toString()
            if(TextUtils.isEmpty(phoneNumber)){
                val noPhoneNumberErr = binding.phoneNumberEntry
                noPhoneNumberErr.error = "Phone number missing"
                noPhoneNumberErr.requestFocus()
            }else{

                if(!isUserSignedIn){
                    signIn()
                }
                findNavController().navigate(
                    SignUpFragmentDirections.actionSignUpFragmentToMessagesFragment2(
                        phoneNumber
                    )
                )

            }
        }

        return binding.root
    }

    private fun signIn() {
        val params = Bundle()
        params.putString(AuthUI.EXTRA_DEFAULT_COUNTRY_CODE, "ng")
        params.putString(AuthUI.EXTRA_DEFAULT_NATIONAL_NUMBER, "23456789")

        val phoneConfigWithDefaultNumber = AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER)
            .setParams(params)
            .build()
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(
                    Arrays.asList(phoneConfigWithDefaultNumber))
                .build(),
            RC_SIGN_IN)
    }

    fun phoneAuthentication(phoneNumber: String){
        
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(this.requireActivity())
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
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