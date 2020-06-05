package com.mobileappdevelopment.themunichquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_settings.view.*
import kotlinx.android.synthetic.main.rules.view.*

class SettingsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.activity_settings, container, false)

        view.imageButton20.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_settingsFragment2_to_lobbyFragment)
        }

        // TODO Buttons navigation

        return view
    }
}
