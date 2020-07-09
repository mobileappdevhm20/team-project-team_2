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
import kotlinx.android.synthetic.main.result.view.*

class ResultFragment : Fragment() {
    lateinit var fd: FirebaseDatabase
    lateinit var auth: FirebaseAuth
    lateinit var dr: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        auth = FirebaseAuth.getInstance()
        fd = FirebaseDatabase.getInstance()
        dr = fd.reference
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.result, container, false)

        val gameKey = requireArguments().getString("gameKey")!!
        val player = requireArguments().getString("player")!!
        val ongoingGameKey = requireArguments().getString("ongoingGameKey")!!
        var scoreUpdated = false
        var yourCorrectAnswers:Int?

        dr.child("games").child(gameKey).child(player).child("score").addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                yourCorrectAnswers = snapshot.getValue(Int::class.java)
                view.yourCorrectAnswers.text = yourCorrectAnswers.toString()
                if(yourCorrectAnswers is Int) {
                    view.opponentCorrectAnswers.text = (4 - yourCorrectAnswers!!).toString()
                }
            }
        }
        )
        dr.child("users").child(auth.uid!!).child("ongoingGames").child(ongoingGameKey).child("completed").setValue(true)

        updatePlayerScore(3, 1)

        view.button_statt.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_resultFragment_to_statisticsFragment)
        }
        view.button_lobby.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_resultFragment_to_lobbyFragment)
        }
        return view

    }

    fun updatePlayerScore(yourCorrectAnswers: Int, opponnentCorrectAnswers: Int) {
        if (yourCorrectAnswers > opponnentCorrectAnswers) {
            val currentScoreRef = dr.child("users").child(auth.uid!!).child("score")

            currentScoreRef.runTransaction(object : Transaction.Handler {
                override fun doTransaction(mutableData: MutableData): Transaction.Result {
                    var p = mutableData.getValue(Int::class.java)
                        ?: return Transaction.success(mutableData)

                    if (p != null) {
                        p += 1
                    }

                    // Set value and report transaction success
                    mutableData.value = p
                    return Transaction.success(mutableData)
                }

                override fun onComplete(
                    databaseError: DatabaseError?,
                    committed: Boolean,
                    currentData: DataSnapshot?
                ) {
                    // Transaction completed
                    Log.d("TAG", "postTransaction:onComplete:")
                }
            })
        }
    }
}