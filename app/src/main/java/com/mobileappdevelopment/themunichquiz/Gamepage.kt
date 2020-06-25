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
import com.google.firebase.database.ktx.getValue
import com.mobileappdevelopment.themunichquiz.model.*
import kotlinx.android.synthetic.main.fragment_gamepage.view.*

class Gamepage : Fragment() {
    lateinit var fd: FirebaseDatabase
    lateinit var auth: FirebaseAuth
    lateinit var dr: DatabaseReference
    val player = "playerOne"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = FirebaseAuth.getInstance()
        fd = FirebaseDatabase.getInstance()
        dr = fd.reference
        lateinit var currentQuestion: Question
        var correctAnswer = 0
        val gameId: String
        Log.d("All questions", dr.child("questions").toString())

        // Create view
        val view = inflater.inflate(R.layout.fragment_gamepage, container, false)

        var userIds: ArrayList<String> = arrayListOf()

        dr.child("users").addListenerForSingleValueEvent( object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("key", snapshot.key.toString())
                userIds.add(snapshot.key.toString())
            }
        })

        if(requireArguments().getString("gameKey") != null) {
            gameId = requireArguments().getString("gameKey")!!
        } else if (requireArguments().getString("opponentId") != null) {
            val pushedGameReference: DatabaseReference = dr.child("games").push()
            pushedGameReference.setValue(Game(questions = listOf(1, 2, 3, 4), playerOne = Player(userId = auth.uid!!), playerTwo = Player(userId = requireArguments().getString("opponentId")!!)))
            val pusehdOngoingGamesReference: DatabaseReference = dr.child("users").child(auth.uid!!).child("ongoingGames").push()
            val pusehdOngoingGamesOpponentReference: DatabaseReference = dr.child("users").child(requireArguments().getString("opponentId")!!).child("ongoingGames").push()
            gameId = pushedGameReference.key!!
            pusehdOngoingGamesReference.setValue(GamesReference(gameId))
            pusehdOngoingGamesOpponentReference.setValue(GamesReference(gameId))
        } else {
            val pushedGameReference: DatabaseReference = dr.child("games").push()
            pushedGameReference.setValue(Game(questions = listOf(1, 2, 3, 4), playerOne = Player(userId = auth.uid!!), playerTwo = Player(userId = userIds.shuffled()[0])))
            val pusehdOngoingGamesReference: DatabaseReference = dr.child("users").child(auth.uid!!).child("ongoingGames").push()
            val pusehdOngoingGamesOpponentReference: DatabaseReference = dr.child("users").child("Random Player").child("ongoingGames").push()
            gameId = pushedGameReference.key!!
            pusehdOngoingGamesReference.setValue(GamesReference(gameId))
            pusehdOngoingGamesOpponentReference.setValue(GamesReference(gameId))
        }

        // Set event listener to current question
        dr.child("games").child(gameId).child(player).child("currentQuestion").addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                // load current question id
                dr.child("games").child(gameId).child("questions").child(snapshot.value.toString()).addValueEventListener(object: ValueEventListener {
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
                increasePlayerScore(gameId)
            }
            goToNextQuestion(gameId, view)
        }

        view.answer2.setOnClickListener { view ->
            if (correctAnswer == 2) {
                increasePlayerScore(gameId)
            }
            goToNextQuestion(gameId, view)
        }
        view.answer3.setOnClickListener { view ->
            if (correctAnswer == 3) {
                increasePlayerScore(gameId)
            }
            goToNextQuestion(gameId, view)
        }
        view.answer4.setOnClickListener { view ->
            if (correctAnswer == 4) {
                increasePlayerScore(gameId)
            }
            goToNextQuestion(gameId, view)
        }
        return view
    }

    fun increasePlayerScore(gameId: String) {
        val currentScoreRef = dr.child("games").child(gameId).child(player).child("score")

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

    fun goToNextQuestion(gameId: String, view: View) {
        val currentQuestionRef = dr.child("games").child(gameId).child(player).child("currentQuestion")

        currentQuestionRef.runTransaction(object : Transaction.Handler {
            override fun doTransaction(mutableData: MutableData): Transaction.Result {
                var p = mutableData.getValue(Int::class.java)
                    ?: return Transaction.success(mutableData)

                if (p != null && p<3) {
                    p += 1
                } else if(p != null) {
                    val bundle = bundleOf("gameKey" to gameId, "player" to player)
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
