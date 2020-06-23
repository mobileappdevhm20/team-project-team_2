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
import kotlinx.android.synthetic.main.signup.*
import kotlinx.android.synthetic.main.signup.view.*

class SignUpFragment : Fragment() {
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.signup, container, false)

        auth = Firebase.auth

        view.button_signup.setOnClickListener { view ->
            val email = text_email.text.toString()
            val password = text_password.text.toString()
            val passwordRepetition = text_password_repeat.text.toString()

            if (!password.equals(passwordRepetition)) {
                Toast.makeText(context, "Passwords are not equal!", Toast.LENGTH_LONG).show()
            } else if (email.isEmpty() || password.isEmpty() || passwordRepetition.isEmpty()) {
                Toast.makeText(context, "No field should be emtpy!", Toast.LENGTH_LONG).show()
            }else {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            view.findNavController().navigate(R.id.action_signUpFragment2_to_loginFragment)
                        } else {
                            Toast.makeText(context, "Signup failed.",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        view.backbutton.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_signUpFragment2_to_loginFragment)
        }

        return view
    }
}
