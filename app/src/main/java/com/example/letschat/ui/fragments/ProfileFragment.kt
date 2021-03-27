package com.example.letschat.ui.fragments

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.letschat.R
import com.example.letschat.data.User
import com.example.letschat.databinding.ProfileFragmentBinding
import com.example.letschat.ui.viewmodels.ProfileViewModel

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel
    private lateinit var binding: ProfileFragmentBinding
    val args:ProfileFragmentArgs by navArgs()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false)

        binding.profiletoolbarmain.title = "Profile"
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        val phoneName = binding.profileDeviceName
        phoneName.text = args.deviceName

        binding.saveButton.setOnClickListener {
            it.hideKeyboard()
            val userName = binding.userName.text.toString().trim()
            if(TextUtils.isEmpty(userName)){
                val userNameErr = binding.usernameField
                userNameErr.error = "Name is required"
            }else{
                val user = User(userName, args.deviceName, null)
                val preferences = activity?.getPreferences(Context.MODE_PRIVATE) ?: return@setOnClickListener
                with(preferences.edit()){
                    putString(getString(R.string.user_name), user.userName)
                    apply()

                }
                findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToMessagesFragment2(args.deviceName))
            }
        }
    }

    fun View.hideKeyboard(){
        val inputMethodManager = getSystemService(context, InputMethodManager::class.java) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }

}