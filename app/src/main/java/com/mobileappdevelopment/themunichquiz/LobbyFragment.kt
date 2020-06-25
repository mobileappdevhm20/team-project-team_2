package com.mobileappdevelopment.themunichquiz

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_lobby.*
import kotlinx.android.synthetic.main.fragment_lobby.view.*

class LobbyFragment : Fragment() {
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

        var userIds: ArrayList<String> = arrayListOf()

        dr.child("users").addChildEventListener( object: ChildEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                ;
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                ;
            }

            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                Log.d("key", snapshot.key.toString())
                userIds.add(snapshot.key.toString())
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                userIds.remove(snapshot.key.toString())
            }
        })
        val view = inflater.inflate(R.layout.fragment_lobby, container, false)

        view.button_ongoinggames.setOnClickListener{view ->
            view.findNavController().navigate(R.id.action_lobbyFragment_to_ongoing_games)
        }

        view.button_rules.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_lobbyFragment_to_rulesFragment)
        }

        view.button_stats.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_lobbyFragment_to_statisticsFragment)
        }

        view.button_playw_friend.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_lobbyFragment_to_playWithFriendFragment)
        }

        view.button_addfriend.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_lobbyFragment_to_addFriendFragment)
        }

        view.button_settings.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_lobbyFragment_to_settingsFragment2)
        }

        view.button_playw_random.setOnClickListener { view ->
            val bundle = bundleOf("opponentId" to userIds.shuffled()[0])
            view.findNavController().navigate(R.id.action_lobbyFragment_to_gamepage, bundle)
        }

        return view
    }

}
