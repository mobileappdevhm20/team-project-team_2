package com.mobileappdevelopment.themunichquiz

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_lobby.view.*
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.login.view.*

class LoginFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.login, container, false)

        view.button_login.setOnClickListener { view->
            val username = text_username.text
            val password = text_password.text

            view.findNavController().navigate(R.id.action_loginFragment_to_lobbyFragment)

            /**
            if(username.isEmpty() || password.isEmpty()) {
                Toast.makeText(context, "Please enter username and password!", Toast.LENGTH_LONG).show()
            } else {
                // TODO: Check if username and password is correct
                // TODO: Navigate to lobby
            }
            **/
        }

        view.button_signup.setOnClickListener{ view ->
            // TODO: Navigate to signup page
        }



        return view
    }
}