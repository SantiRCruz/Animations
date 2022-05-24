package com.example.firstanimations.ui.players.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firstanimations.core.Constants
import com.example.firstanimations.data.models.PlayerEntity
import com.example.firstanimations.databinding.ItemPlayersBinding

class PlayersAdapter(private val players : List<PlayerEntity>):RecyclerView.Adapter<PlayersAdapter.PlayersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersViewHolder {
        val itemBinding = ItemPlayersBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PlayersViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PlayersViewHolder, position: Int) {
        holder.bind(players[position])
    }

    override fun getItemCount(): Int = players.size

    inner class PlayersViewHolder(private val binding:ItemPlayersBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(player:PlayerEntity){
            binding.txtNamePlayer.text = player.name_player
            binding.txtPosition.text = player.position
            binding.txtNumber.text = player.num_jersey
        }
    }
}