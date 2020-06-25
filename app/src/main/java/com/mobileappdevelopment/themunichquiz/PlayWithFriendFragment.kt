package com.mobileappdevelopment.themunichquiz

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.mobileappdevelopment.themunichquiz.model.User
import kotlinx.android.synthetic.main.fragment_play_with_friend.*
import kotlinx.android.synthetic.main.fragment_play_with_friend.view.*
import kotlinx.android.synthetic.main.fragment_play_with_friend.view.recyclerView

/**
 * A simple [Fragment] subclass.
 */
class PlayWithFriendFragment : Fragment() {
    lateinit var fd: FirebaseDatabase
    lateinit var auth: FirebaseAuth
    lateinit var dr: DatabaseReference

    // variables für Adapter
    var adapter : FriendsAdapter = FriendsAdapter(listOf())
    lateinit var mainMenu : RecyclerView
    //private val adapter by lazy { FriendsAdapter(arrayListOf("Test1", "Test2")) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = FirebaseAuth.getInstance()
        fd = FirebaseDatabase.getInstance()
        dr = fd.reference

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_play_with_friend, container, false)
        // initial ReciclerView
        mainMenu = view.findViewById(R.id.recyclerView)as RecyclerView
        mainMenu.layoutManager = LinearLayoutManager(context)
        mainMenu.adapter = adapter


        var userIds: ArrayList<String> = arrayListOf()
        var users: ArrayList<String> = arrayListOf()
        dr.child("users").child(auth.uid!!).child("friends").addChildEventListener( object:
            ChildEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                val userIndex = userIds.indexOf(snapshot.key.toString())
                users[userIndex] = snapshot.getValue<String>()!!
                adapter = FriendsAdapter(users)
                mainMenu.adapter = adapter
            }

            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                Log.d("onChildAdded", snapshot.toString())
                userIds.add(snapshot.key.toString())
                users.add(snapshot.getValue<String>()!!)
                adapter = FriendsAdapter(users)
                mainMenu.adapter = adapter
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                val userIndex = userIds.indexOf(snapshot.key.toString())
                users.removeAt(userIndex)
                adapter = FriendsAdapter(users)
                mainMenu.adapter = adapter
            }

        })

        view.backButton.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_playWithFriendFragment_to_lobbyFragment)
        }

        return view
    }




}
