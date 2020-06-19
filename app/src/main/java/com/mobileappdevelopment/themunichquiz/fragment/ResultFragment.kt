package com.mobileappdevelopment.themunichquiz.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.mobileappdevelopment.themunichquiz.R
import kotlinx.android.synthetic.main.result.view.*

class ResultFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.result, container, false)

        view.button_statt.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_resultFragment_to_statisticsFragment)
        }
        view.back.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_resultFragment_to_lobbyFragment)
        }
        return view

    }
}