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

        view.button_stats.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_lobbyFragment_to_statisticsFragment)
        }

        view.button_playw_friend.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_lobbyFragment_to_playWithFriendFragment)
        }

        view.button_addfriend.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_lobbyFragment_to_addFriendFragment)
        }

        // TODO navigation to AddAFriend

        // TODO navigation to PlayWRandom

        return view
    }

}
