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

//        button_settings.setOnClickListener {
//
//        }
//
//        button_addfriend.setOnClickListener {
//
//        }
//
//        button_playw_friend.setOnClickListener {
//
//        }
//
//        button_playw_random.setOnClickListener {
//
//        }
//
//        button_rules.setOnClickListener {
//
//        }
//
//        button_stats.setOnClickListener {
//
//        }
    }

}
