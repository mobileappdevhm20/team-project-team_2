package com.mobileappdevelopment.themunichquiz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mobileappdevelopment.themunichquiz.model.User
import kotlinx.android.synthetic.main.friends.view.*
import kotlinx.android.synthetic.main.statistics.view.*

class StatisticAdapter (var stats: List<User>) :
    RecyclerView.Adapter<StatisticAdapter.StatsViewHolder>() {

    class StatsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(statistic: User) {
            itemView.gamestatistic.text = statistic.email + ": " + statistic.score.toString()
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