package com.mobileappdevelopment.themunichquiz.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.mobileappdevelopment.themunichquiz.R
import kotlinx.android.synthetic.main.rules.view.*

class RulesFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.rules, container, false)

        view.button_back.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_rulesFragment_to_lobbyFragment)
        }

        view.button_settings.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_rulesFragment_to_settingsFragment2)
        }

        return view
    }
}