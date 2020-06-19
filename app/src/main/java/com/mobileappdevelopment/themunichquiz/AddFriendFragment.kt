package com.mobileappdevelopment.themunichquiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.add_friend.view.*


/**
 * send firebase massage want to be friends
 */
class AddFriendFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.add_friend, container, false)

        view.back_button.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_addFriendFragment_to_lobbyFragment)
        }

        view.button_add.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_addFriendFragment_to_lobbyFragment)
        }

        return view
    }
}