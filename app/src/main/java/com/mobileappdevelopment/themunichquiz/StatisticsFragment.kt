package com.mobileappdevelopment.themunichquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_statistics.view.*
import java.time.Year

class StatisticsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_statistics, container, false)

        view.backButton.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_statisticsFragment_to_lobbyFragment)
        }

        // TODO navigation to lobby

        // TODO inflate StatisticsText from database

        return view
    }

}
