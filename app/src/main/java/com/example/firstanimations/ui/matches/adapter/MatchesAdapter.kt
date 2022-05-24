package com.example.firstanimations.ui.matches.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.firstanimations.data.models.relations.MatchAndStadium
import com.example.firstanimations.databinding.ItemMatchesBinding

class MatchesAdapter(private val matchAndStadiums: List<MatchAndStadium>):RecyclerView.Adapter<MatchesAdapter.MatchesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesViewHolder {
        val itemBinding = ItemMatchesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MatchesViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MatchesViewHolder, position: Int) {
        holder.bind(matchAndStadiums[position])
    }

    override fun getItemCount(): Int = matchAndStadiums.size

    inner class MatchesViewHolder(private val binding : ItemMatchesBinding ):RecyclerView.ViewHolder(binding.root){
        fun bind(matchAndStadium: MatchAndStadium){
            binding.txtAwayTeam.text = matchAndStadium.awayTeamEntity.nameTeam
            binding.txtHomeTeam.text = matchAndStadium.homeTeamEntity.nameTeam
            binding.txtStadium.text = matchAndStadium.stadiumEntity.nameStadium
            binding.txtDate.text = matchAndStadium.match.date
        }
    }
}
