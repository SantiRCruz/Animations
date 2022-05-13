package com.example.firstanimations.ui.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.firstanimations.R
import com.example.firstanimations.UpdateUserActivity
import com.example.firstanimations.core.Constants
import com.example.firstanimations.core.Result
import com.example.firstanimations.data.models.UserEntity
import com.example.firstanimations.databinding.UserItemBinding
import com.example.firstanimations.presentation.UserViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeAdapter(private val users: List<UserEntity>,private val viewModel : UserViewModel):RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val itemBinding = UserItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HomeViewHolder(itemBinding,viewModel,parent.context)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size


    inner class HomeViewHolder(private val binding : UserItemBinding,private val viewModel: UserViewModel,private val context:Context):RecyclerView.ViewHolder(binding.root){
        fun bind(user:UserEntity){
            binding.lastNameTxt.text = user.last_name
            binding.emailTxt.text = user.email
            if (user.blocked){
                binding.statusTxt.text = "locked"
            }else{
                binding.statusTxt.text = "not locked"
            }
            val scope = CoroutineScope(Dispatchers.Main)
            binding.imgDelete.setOnClickListener {
                scope.launch {
                    viewModel.deleteUser(user).collect {
                        when(it){
                            is Result.Success->{
                                Snackbar.make(binding.root,"Deleted correctly",Snackbar.LENGTH_SHORT).show()
                                Navigation.findNavController(binding.root).navigate(R.id.action_nav_home_self)
                            }
                            is Result.Failure->{
                                Snackbar.make(binding.root,"Sorry, could not delete ",Snackbar.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
            binding.imgBlock.setOnClickListener {
                scope.launch {
                    viewModel.updateUser(UserEntity(user.id_user,user.name,user.email,user.last_name,user.password,user.profile,!user.blocked)).collect {
                        when(it){
                            is Result.Success->{
                                Snackbar.make(binding.root,"updated correctly",Snackbar.LENGTH_SHORT).show()
                                Navigation.findNavController(binding.root).navigate(R.id.action_nav_home_self)
                            }
                            is Result.Failure->{
                                Snackbar.make(binding.root,"Sorry, could not locked ${user.name} ",Snackbar.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
            binding.imgEdit.setOnClickListener {
                Constants.USER_UPDATE = user
                val i = Intent(context,UpdateUserActivity::class.java)
                context.startActivity(i)
            }
        }
    }
}