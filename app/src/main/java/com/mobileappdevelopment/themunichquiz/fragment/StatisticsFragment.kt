package com.mobileappdevelopment.themunichquiz.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.mobileappdevelopment.themunichquiz.R
import kotlinx.android.synthetic.main.statistics.view.*

class StatisticsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.statistics, container, false)

        view.backButton.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_statisticsFragment_to_lobbyFragment)
        }

        // TODO navigation to lobby

        // TODO inflate StatisticsText from database

        return view
    }

}