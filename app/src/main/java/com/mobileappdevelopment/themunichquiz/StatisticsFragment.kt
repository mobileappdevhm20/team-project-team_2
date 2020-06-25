package com.mobileappdevelopment.themunichquiz

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.mobileappdevelopment.themunichquiz.model.User
import kotlinx.android.synthetic.main.fragment_statistics.view.*
import java.time.Year

class StatisticsFragment : Fragment() {
    lateinit var fd: FirebaseDatabase
    lateinit var auth: FirebaseAuth
    lateinit var dr: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = FirebaseAuth.getInstance()
        fd = FirebaseDatabase.getInstance()
        dr = fd.reference

        val view =  inflater.inflate(R.layout.fragment_statistics, container, false)

        view.backButton.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_statisticsFragment_to_lobbyFragment)
        }


        var userIds: ArrayList<String> = arrayListOf()
        var users: ArrayList<User> = arrayListOf()

        dr.child("users").addChildEventListener( object: ChildEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                val userIndex = userIds.indexOf(snapshot.key.toString())
                users[userIndex] = snapshot.getValue<User>()!!
                view.StatisticsText.text = users.toString()
            }

            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                Log.d("onChildAdded", snapshot.toString())
                userIds.add(snapshot.key.toString())
                users.add(snapshot.getValue<User>()!!)
                view.StatisticsText.text = users.toString()
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                val userIndex = userIds.indexOf(snapshot.key.toString())
                users.removeAt(userIndex)
                view.StatisticsText.text = users.toString()
            }

        })

        return view
    }
}
