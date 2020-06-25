package com.mobileappdevelopment.themunichquiz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mobileappdevelopment.themunichquiz.model.GamesReference
import kotlinx.android.synthetic.main.friends.view.*
import kotlinx.android.synthetic.main.ongoinggames.view.*

class OnGoingGameAdapter(var Games : List<GamesReference>) :
    RecyclerView.Adapter<OnGoingGameAdapter.OnGoingGameViewHolder>() {

        class OnGoingGameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bind(game: GamesReference) {
                itemView.games.text = game.gameId
                itemView.games.setOnClickListener { view ->
                    val bundle = bundleOf("gameKey" to game.gameId)
                    //Todo correct navigation
                    view.findNavController().navigate(R.id.action_lobbyFragment_to_gamepage, bundle)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnGoingGameViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.ongoinggames, parent, false)
            return OnGoingGameViewHolder(itemView)
        }

        override fun getItemCount(): Int = Games.size

        override fun onBindViewHolder(holder: OnGoingGameViewHolder, position: Int) =
            holder.bind(Games.get(position))

    }