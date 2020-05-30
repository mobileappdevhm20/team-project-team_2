package com.mobileappdevelopment.themunichquiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.start_page.view.*

class StartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // TODO: Button click

        val view = inflater.inflate(R.layout.start_page, container, false)

        view.button_start.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_startFragment3_to_loginFragment)
        }

        return view
    }
}