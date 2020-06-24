package com.mobileappdevelopment.themunichquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.Sampler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.mobileappdevelopment.themunichquiz.model.Game
import com.mobileappdevelopment.themunichquiz.model.Question
import kotlinx.android.synthetic.main.activity_gamepage.view.*
import kotlinx.android.synthetic.main.fragment_gamepage.view.*

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
        lateinit var currentQuestion: Question
        var correctAnswer = 0
        Log.d("All questions", dr.child("questions").toString())

        // Create view
        val view = inflater.inflate(R.layout.fragment_gamepage, container, false)

        // Create new game
        val pushedGameReference: DatabaseReference = dr.child("games").push()
        pushedGameReference.setValue(Game(questions = listOf(1, 2, 3, 4), playerOne = auth.uid!!, playerTwo = "test"))

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
                                view.question.text = "Question: " + currentQuestion.question
                                correctAnswer = 1
                                view.answer1.text = currentQuestion.optionA
                                view.answer2.text = currentQuestion.optionB
                                view.answer3.text = currentQuestion.optionC
                                view.answer4.text = currentQuestion.optionD
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

        view.answer1.setOnClickListener { view ->
            if (correctAnswer == 1) {
                increasePlayerScore(pushedGameReference)
            }
            goToNextQuestion(pushedGameReference, view)
        }

        view.answer2.setOnClickListener { view ->
            if (correctAnswer == 2) {
                increasePlayerScore(pushedGameReference)
            }
            goToNextQuestion(pushedGameReference, view)
        }
        view.answer3.setOnClickListener { view ->
            if (correctAnswer == 3) {
                increasePlayerScore(pushedGameReference)
            }
            goToNextQuestion(pushedGameReference, view)
        }
        view.answer4.setOnClickListener { view ->
            if (correctAnswer == 4) {
                increasePlayerScore(pushedGameReference)
            }
            goToNextQuestion(pushedGameReference, view)
        }
        return view
    }

    fun increasePlayerScore(pushedGameReference: DatabaseReference) {
        val currentScoreRef = dr.child("games").child(pushedGameReference.key!!).child("playerOneScore")

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

    fun goToNextQuestion(pushedGameReference: DatabaseReference, view: View) {
        val currentQuestionRef = dr.child("games").child(pushedGameReference.key!!).child("currentQuestion")

        currentQuestionRef.runTransaction(object : Transaction.Handler {
            override fun doTransaction(mutableData: MutableData): Transaction.Result {
                var p = mutableData.getValue(Int::class.java)
                    ?: return Transaction.success(mutableData)

                if (p != null && p<3) {
                    p += 1
                } else if(p != null) {
                    val bundle = bundleOf("gameKey" to pushedGameReference.key!!)
                    view.findNavController().navigate(R.id.action_gamepage_to_resultFragment, bundle)
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
