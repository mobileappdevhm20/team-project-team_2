package com.mobileappdevelopment.themunichquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.Sampler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.mobileappdevelopment.themunichquiz.model.Game
import com.mobileappdevelopment.themunichquiz.model.Question
import kotlinx.android.synthetic.main.activity_gamepage.view.*

class Gamepage : Fragment() {
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
        var currentQuestion: Question
        Log.d("All questions", dr.child("questions").toString())

        // Create view
        val view = inflater.inflate(R.layout.activity_gamepage, container, false)

        // Create new game
        val pushedGameReference: DatabaseReference = dr.child("games").push()
        pushedGameReference.setValue(Game(listOf(1, 2, 3, 4), auth.uid!!, "test"))

        // Set event listener to current question
        dr.child("games").child(pushedGameReference.key!!).child("currentQuestion").addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                // load current question id
                dr.child("games").child(pushedGameReference.key!!).child("questions").child(snapshot.value.toString()).addValueEventListener(object: ValueEventListener {
                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                    override fun onDataChange(snapshot: DataSnapshot) {
                        // Load current Question
                        dr.child("questions").child(snapshot.value.toString()).addValueEventListener(object: ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                currentQuestion = Question(dataSnapshot.child("question").value.toString(), dataSnapshot.child("optionA").value.toString(),
                                    dataSnapshot.child("optionB").value.toString(), dataSnapshot.child("optionC").value.toString(), dataSnapshot.child("optionD").value.toString())
                                // Update UI
                                view.textView2.text = "Question: " + currentQuestion.question
                                // TODO fill questions
                            }
                            override fun onCancelled(p0: DatabaseError) {
                                // Failed to read value
                                Log.e("Error getting questiom","Couldn't get question")
                            }
                        })
                    }
                })

            }

        })

        view.imageButton2.setOnClickListener { view ->
            val currentQuestionRef = dr.child("games").child(pushedGameReference.key!!).child("currentQuestion")

            currentQuestionRef.runTransaction(object : Transaction.Handler {
                override fun doTransaction(mutableData: MutableData): Transaction.Result {
                    var p = mutableData.getValue(Int::class.java)
                        ?: return Transaction.success(mutableData)

                    if (p != null) {
                        p = p + 1
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

        view.imageButton3.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_gamepage_to_resultFragment)
        }
        view.imageButton4.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_gamepage_to_resultFragment)
        }
        view.imageButton5.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_gamepage_to_resultFragment)
        }
        return view
    }

}
