package com.mobileappdevelopment.themunichquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.mobileappdevelopment.themunichquiz.model.User
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.activity_signup.view.*
import kotlinx.android.synthetic.main.activity_signup.view.text_email
import kotlinx.android.synthetic.main.activity_signup.view.text_password
import kotlinx.android.synthetic.main.activity_signup.view.text_password_repeat
import java.util.*

class SignUpFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var fd: FirebaseDatabase
    lateinit var dr: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_signup, container, false)
        fd = FirebaseDatabase.getInstance()
        dr = fd.reference
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
                            auth.uid?.let {
                                dr.child("users").child(it)
                                    .setValue(User(email)).addOnCompleteListener(object : OnCompleteListener<Void> {
                                        override fun onComplete(p0: Task<Void>) {
                                            view.findNavController().navigate(R.id.action_signUpFragment2_to_loginFragment)
                                        }
                                    })
                            }
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
