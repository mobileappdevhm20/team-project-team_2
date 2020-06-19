package com.mobileappdevelopment.themunichquiz.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.mobileappdevelopment.themunichquiz.R
import kotlinx.android.synthetic.main.activity_gamepage.view.*

class GamepageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_gamepage, container, false)
        view.imageButton.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_gamepage_to_lobbyFragment)
        }

        view.imageButton2.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_gamepage_to_resultFragment)
        }
        view.imageButton3.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_gamepage_to_resultFragment)
        }
        view.imageButton4.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_gamepage_to_resultFragment)
        }
        view.imageButton5.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_gamepage_to_resultFragment)
        }
        return view
    }

}
