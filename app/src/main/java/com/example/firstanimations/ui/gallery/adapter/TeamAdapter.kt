package com.example.firstanimations.ui.gallery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.firstanimations.R
import com.example.firstanimations.data.models.TeamEntity
import com.example.firstanimations.databinding.ItemTeamsBinding
import com.example.firstanimations.ui.gallery.GalleryFragmentDirections

class TeamAdapter (private val teams : List<TeamEntity>):RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val itemBinding = ItemTeamsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TeamViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bind(teams[position])
    }

    override fun getItemCount(): Int = teams.size

    inner class TeamViewHolder(private val binding: ItemTeamsBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(team:TeamEntity){
            binding.txtName.text = team.nameTeam
            binding.txtPoints.text = team.points
            binding.root.setOnClickListener {
                val action = GalleryFragmentDirections.actionNavTeamToPlayersFragment(team.idTeam,team.flag)
                Navigation.findNavController(binding.root).navigate(action)
            }
        }
    }


}