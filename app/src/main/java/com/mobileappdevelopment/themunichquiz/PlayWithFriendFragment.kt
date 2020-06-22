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
import kotlinx.android.synthetic.main.fragment_play_with_friend.view.*

/**
 * A simple [Fragment] subclass.
 */
class PlayWithFriendFragment : Fragment() {
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
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_play_with_friend, container, false)

        var userIds: ArrayList<String> = arrayListOf()

        dr.child("users").child(auth.uid!!).child("friends").addChildEventListener( object:
            ChildEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                Log.d("onChildChanged", snapshot.toString())
                userIds[Integer.parseInt(snapshot.key!!)-1] = snapshot.getValue<String>()!!
                view.Playw_FriendText.text = userIds.toString()
            }

            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                Log.d("onChildAdded", snapshot.toString())
                userIds.add(Integer.parseInt(snapshot.key!!)-1, snapshot.getValue<String>()!!)
                view.Playw_FriendText.text = userIds.toString()
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                Log.d("onChildRemoved", snapshot.toString())
                userIds.removeAt(Integer.parseInt(snapshot.key!!)-1)
                view.Playw_FriendText.text = userIds.toString()
            }

        })

        view.backButton.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_playWithFriendFragment_to_lobbyFragment)
        }
        // TODO navigation to lobby

        // TODO inflate Playw_FriendText from database

        return view
    }

}
