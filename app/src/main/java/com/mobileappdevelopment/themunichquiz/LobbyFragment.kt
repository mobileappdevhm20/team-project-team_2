package com.mobileappdevelopment.themunichquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_lobby.*
import kotlinx.android.synthetic.main.fragment_lobby.view.*

class LobbyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_lobby, container, false)

        view.button_rules.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_lobbyFragment_to_rulesFragment)
        }

        // TODO navigation to AddAFriend

        // TODO navigation to PlayWFriend

        // TODO navigation to PlayWRandom

        // TODO navigation to Stats

        // TODO navigation to Rules
        return view
    }

}
