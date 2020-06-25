package com.mobileappdevelopment.themunichquiz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.friends.view.*
import kotlinx.android.synthetic.main.statistics.view.*

class StatisticAdapter (var stats: List<String>) :
    RecyclerView.Adapter<StatisticAdapter.StatsViewHolder>() {

    class StatsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(statistic: String) {
            itemView.gamestatistic.text = statistic
            itemView.friends.setOnClickListener { view ->
                val bundle = bundleOf("opponentId" to statistic)
                TODO("richtige navigation hinzufügen")
                view.findNavController().navigate(R.id.action_lobbyFragment_to_gamepage, bundle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.statistics, parent, false)
        return StatsViewHolder(itemView)
    }

    override fun getItemCount(): Int = stats.size

    override fun onBindViewHolder(holder: StatsViewHolder, position: Int) =
        holder.bind(stats.get(position))

}