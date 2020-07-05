package com.mobileappdevelopment.themunichquiz

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.mobileappdevelopment.themunichquiz.model.Game
import com.mobileappdevelopment.themunichquiz.model.User
import kotlinx.android.synthetic.main.add_friend.view.*

class AddFriendFragment : Fragment(){
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

        val view = inflater.inflate(R.layout.add_friend, container, false)

        view.button_backfromaddfr.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_addFriendFragment_to_lobbyFragment)
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
            }

            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                Log.d("onChildAdded", snapshot.toString())
                userIds.add(snapshot.key.toString())
                users.add(snapshot.getValue<User>()!!)
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                val userIndex = userIds.indexOf(snapshot.key.toString())
                users.removeAt(userIndex)
            }

        })

        view.button_add.setOnClickListener { _ ->
            if(view.text_username.text.isNullOrEmpty()) {
                Toast.makeText(context, "Please enter a username!",
                    Toast.LENGTH_SHORT).show()
            } else {
                Log.d("users", users.toString())
                val searchedUser: User? = users.find { it.email == view.text_username.text.toString() }
                if(searchedUser == null) {
                    Toast.makeText(context, "No user found!",
                        Toast.LENGTH_SHORT).show()
                } else {
                    val pushedGameReference: DatabaseReference = dr.child("users").child(auth.uid!!).child("friends").push()
                    pushedGameReference.setValue(userIds[users.indexOf(searchedUser)])
                    view.findNavController().navigate(R.id.action_addFriendFragment_to_lobbyFragment)
                }
            }
        }
        // TODO Button clicks

        return view
    }
}