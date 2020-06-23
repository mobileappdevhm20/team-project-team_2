package com.mobileappdevelopment.themunichquiz.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mobileappdevelopment.themunichquiz.R
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.login.view.*

class LoginFragment : Fragment() {
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.login, container, false)

        auth = Firebase.auth

        view.button_login.setOnClickListener { view->
            val username = text_username.text.toString()
            val password = text_password.text.toString()

            if(username.isEmpty() || password.isEmpty()) {
                Toast.makeText(context, "Please enter username and password!", Toast.LENGTH_LONG).show()
            } else {
                auth.signInWithEmailAndPassword(username, password)
                    .addOnCompleteListener(requireActivity()){task->
                        if (task.isSuccessful) {
                            view.findNavController().navigate(R.id.action_loginFragment_to_lobbyFragment)
                        } else {
                            Toast.makeText(context, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        view.button_signup.setOnClickListener{ view ->
            view.findNavController().navigate(R.id.action_loginFragment_to_signUpFragment2)
        }

        return view
    }


}