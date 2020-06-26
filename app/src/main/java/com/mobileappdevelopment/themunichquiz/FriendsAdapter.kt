package com.mobileappdevelopment.themunichquiz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.friends.view.*

class FriendsAdapter(var friends: List<String>) :
    RecyclerView.Adapter<FriendsAdapter.FriendViewHolder>() {

    class FriendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(friend: String) {
            itemView.friends.text = friend
            itemView.friends.setOnClickListener { view ->
                val bundle = bundleOf("opponentId" to friend)
                view.findNavController().navigate(R.id.action_playWithFriendFragment_to_gameFragment, bundle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.friends, parent, false)
        return FriendViewHolder(itemView)
    }

    override fun getItemCount(): Int = friends.size

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) =
        holder.bind(friends.get(position))

}