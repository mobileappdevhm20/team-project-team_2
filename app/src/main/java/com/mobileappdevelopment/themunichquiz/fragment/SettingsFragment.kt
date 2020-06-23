package com.mobileappdevelopment.themunichquiz.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.mobileappdevelopment.themunichquiz.R
import kotlinx.android.synthetic.main.settings.view.*

class SettingsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.settings, container, false)

        view.imageButton20.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_settingsFragment2_to_lobbyFragment)
        }

        // TODO Buttons navigation

        return view
    }
}
