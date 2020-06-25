package com.mobileappdevelopment.themunichquiz

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.mobileappdevelopment.themunichquiz.R
import kotlinx.android.synthetic.main.fragment_ongoing_games.view.*

class OngoingGamesFragment: Fragment() {
    lateinit var fd: FirebaseDatabase
    lateinit var auth: FirebaseAuth
    lateinit var dr: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_ongoing_games, container, false)

        auth = FirebaseAuth.getInstance()
        fd = FirebaseDatabase.getInstance()
        dr = fd.reference

        var ongoingGamesId: ArrayList<String> = arrayListOf()
        var ongoingGames: ArrayList<String> = arrayListOf()

        dr.child("users").child(auth.uid!!).child("ongoingGames").addChildEventListener( object:
            ChildEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                Log.d("onChildChanged", snapshot.toString())
                ongoingGames[ongoingGamesId.indexOf(snapshot.key.toString())] = snapshot.getValue<String>()!!
                view.ongoing_games_text.text = ongoingGames.toString()
            }

            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                Log.d("onChildAdded", snapshot.toString())
                ongoingGamesId.add(snapshot.key.toString())
                ongoingGames.add(snapshot.getValue<String>()!!)
                view.ongoing_games_text.text = ongoingGames.toString()
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                Log.d("onChildRemoved", snapshot.toString())
                val ongoingGamesIndex = ongoingGamesId.indexOf(snapshot.key.toString())
                ongoingGames.removeAt(ongoingGamesIndex)
                view.ongoing_games_text.text = ongoingGames.toString()
            }

        })

        view.backButton.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_ongoingGamesFragment_to_lobbyFragment)
        }

        return view
    }
}
