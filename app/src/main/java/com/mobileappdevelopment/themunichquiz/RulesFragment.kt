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
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.login.view.*
import kotlinx.android.synthetic.main.rules.view.*

class RulesFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.rules, container, false)

        view.button_back.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_rulesFragment_to_lobbyFragment)
        }

        // TODO Buttons navigation

        return view
    }
}