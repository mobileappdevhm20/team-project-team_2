package com.mobileappdevelopment.themunichquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_signup.view.*

class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_signup, container, false)

        view.signupbtn.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_signUpFragment2_to_loginFragment)
        }
        view.backbutton.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_signUpFragment2_to_loginFragment)
        }

        return view
    }
}
