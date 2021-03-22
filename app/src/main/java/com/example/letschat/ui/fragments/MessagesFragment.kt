package com.example.letschat.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.letschat.R
import com.example.letschat.databinding.FragmentMessagesBinding


private const val DEVICE_NAME = "deviceName"


class MessagesFragment : Fragment() {

    private var deviceName: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            deviceName = it.getString(DEVICE_NAME)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentMessagesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_messages,container,false)
        Log.i("Messages","Fragment view created")

        Toast.makeText(context, "$deviceName", Toast.LENGTH_LONG).show()
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param deviceName Parameter 1.
         * @return A new instance of fragment MessagesFragment.
         */
        @JvmStatic
        fun newInstance(deviceName: String) =
            MessagesFragment().apply {
                arguments = Bundle().apply {
                    putString(DEVICE_NAME, deviceName)
                }
            }
    }
}