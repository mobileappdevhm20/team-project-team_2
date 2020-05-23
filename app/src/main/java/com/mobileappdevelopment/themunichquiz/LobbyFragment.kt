package com.mobileappdevelopment.themunichquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_lobby.*

class LobbyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lobby, container, false)
    // Change something
        // TODO navigation to AddAFriend

        // TODO navigation to PlayWFriend

        // TODO navigation to PlayWRandom

        // TODO navigation to Stats

        // TODO navigation to Rules

    }

}
