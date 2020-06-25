package com.mobileappdevelopment.themunichquiz

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.mobileappdevelopment.themunichquiz.R
import com.mobileappdevelopment.themunichquiz.model.GamesReference
import kotlinx.android.synthetic.main.fragment_ongoing_games.view.*

class OngoingGamesFragment: Fragment() {
    lateinit var fd: FirebaseDatabase
    lateinit var auth: FirebaseAuth
    lateinit var dr: DatabaseReference

    //varables vor RecylerView an Adapter
    var adapter : OnGoingGameAdapter = OnGoingGameAdapter(listOf())
    lateinit var mainMenu : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_ongoing_games, container, false)

        //initial reciclerView
        mainMenu = view.findViewById(R.id.recyclerViewongoingGames)as RecyclerView
        mainMenu.layoutManager = LinearLayoutManager(context)
        mainMenu.adapter = adapter

        auth = FirebaseAuth.getInstance()
        fd = FirebaseDatabase.getInstance()
        dr = fd.reference

        var ongoingGamesId: ArrayList<String> = arrayListOf()
        var ongoingGames: ArrayList<GamesReference> = arrayListOf()

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
                ongoingGames[ongoingGamesId.indexOf(snapshot.key.toString())] = snapshot.getValue<GamesReference>()!!
                //Todo ongoingGames is not a List<String>
                adapter = OnGoingGameAdapter(listOf())
                mainMenu.adapter = adapter
            }

            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                Log.d("onChildAdded", snapshot.toString())
                ongoingGamesId.add(snapshot.key.toString())
                ongoingGames.add(snapshot.getValue<GamesReference>()!!)
                //Todo ongoingGames is not a List<String>
                adapter = OnGoingGameAdapter(listOf())
                mainMenu.adapter = adapter            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                Log.d("onChildRemoved", snapshot.toString())
                val ongoingGamesIndex = ongoingGamesId.indexOf(snapshot.key.toString())
                ongoingGames.removeAt(ongoingGamesIndex)
                // Todo ongoingGames is not a List<String>
                adapter = OnGoingGameAdapter(listOf())
                mainMenu.adapter = adapter            }

        })

        view.backButton.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_ongoingGamesFragment_to_lobbyFragment)
        }

        return view
    }
}
