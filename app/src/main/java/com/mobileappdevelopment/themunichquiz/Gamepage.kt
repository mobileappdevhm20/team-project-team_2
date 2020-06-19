package com.mobileappdevelopment.themunichquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_gamepage.view.*


/**
 * Communication between both users
 * should be on a seperatie Thread
 *
 *      User1  ----  play? ---> User2
 *      User1  <---  yes ------ User2
 *      User1  ----  Permisson database ---> User2
 *
 *  Two seperateThread for send an recive in onCreateView
 */
class Gamepage : Fragment() {

    /**
     * saved all questions stored in the database
     */
    data class Question(
        val question : String,
        val answers : List<String>
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // TODO Count the number of right and wrong questions


        val view = inflater.inflate(R.layout.activity_gamepage, container, false)
        view.imageButton.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_gamepage_to_lobbyFragment)
        }

        view.imageButton2.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_gamepage_to_resultFragment)
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

    /**
     * select five random questions from the database
     * return: list of the Quesions
     */
    fun chooseQuestions() : List<Question>? {
        //TODO Select five questions
        return null
    }


    fun storeQuestionsGame(questions : List<Question>){
        //TODO store questens in new database
    }

    fun sendPermission(userid : String){
        // TODO send Pushnotification to start the game
    }

}
