package com.mobileappdevelopment.themunichquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_play_with_friend.view.*

/**
 * A simple [Fragment] subclass.
 */
class PlayWithFriendFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_play_with_friend, container, false)

        view.backButton.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_playWithFriendFragment_to_lobbyFragment)
        }
        // TODO navigation to lobby

        // TODO inflate Playw_FriendText from database

        return view
    }

}
