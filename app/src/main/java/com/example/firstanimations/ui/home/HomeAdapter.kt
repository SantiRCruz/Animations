package com.example.firstanimations.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firstanimations.data.models.UserEntity
import com.example.firstanimations.databinding.UserItemBinding
import com.example.firstanimations.presentation.UserViewModel

class HomeAdapter(private val users: List<UserEntity>,private val viewModel : UserViewModel):RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val itemBinding = UserItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HomeViewHolder(itemBinding,viewModel)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size


    inner class HomeViewHolder(binding : UserItemBinding,viewModel: UserViewModel):RecyclerView.ViewHolder(binding.root){
        fun bind(user:UserEntity){

        }
    }
}