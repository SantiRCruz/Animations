package com.example.firstanimations.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstanimations.R
import com.example.firstanimations.core.Constants
import com.example.firstanimations.core.Result
import com.example.firstanimations.data.local.AppDatabase
import com.example.firstanimations.databinding.FragmentHomeBinding
import com.example.firstanimations.presentation.UserViewModel
import com.example.firstanimations.presentation.UserViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<UserViewModel> {
        UserViewModelFactory(
            AppDatabase.getUserDatabase(
                requireContext()
            ).UserDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding = FragmentHomeBinding.bind(view)

        setUpUsers()

    }

    private fun setUpUsers() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.fetchUserAll().collect {
                when (it) {
                    is Result.Success -> {
                        Log.e( "setUpUsers: ", it.data.toString())
                        if (it.data.isNullOrEmpty()) {
                            Snackbar.make(
                                binding.root,
                                "there is no information in the database",
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }else{
                            val adapter  = HomeAdapter(it.data,viewModel)
                            binding.rvUsers.layoutManager = LinearLayoutManager(requireContext())
                            binding.rvUsers.adapter = adapter
                        }
                    }
                    is Result.Failure -> {
                        Snackbar.make(binding.root, "Error! ${it.exception}", Snackbar.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

}