package com.example.firstanimations.ui.teams

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstanimations.R
import com.example.firstanimations.core.Constants
import com.example.firstanimations.core.Result
import com.example.firstanimations.data.local.AppDatabase
import com.example.firstanimations.databinding.FragmentTeamsBinding
import com.example.firstanimations.presentation.TeamViewModel
import com.example.firstanimations.presentation.TeamViewModelFactory
import com.example.firstanimations.ui.gallery.adapter.TeamAdapter
import kotlinx.coroutines.flow.collect


class TeamsFragment : Fragment(R.layout.fragment_teams) {
    private lateinit var binding : FragmentTeamsBinding
    private val viewModel by viewModels<TeamViewModel> { TeamViewModelFactory(AppDatabase.getTeamDatabase(requireContext()).TeamDao())  }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTeamsBinding.bind(view)
        getDataNames()
        clicks()
    }

    private fun clicks() {
        binding.switch1.setOnClickListener {
            if (binding.switch1.isChecked) {
                getDataPoints()
                binding.switch1.text = "Points"
            }else{
                binding.switch1.text = "Team Name"
                getDataNames()
            }
        }
    }

    private fun getDataPoints() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.getMatchByPointsAsc().collect{
                    when(it){
                        is Result.Success ->{
                            val adapter = TeamAdapter(it.data)
                            binding.rvTeams.layoutManager = LinearLayoutManager(requireContext())
                            binding.rvTeams.adapter = adapter
                        }
                        is Result.Failure ->{
                        }
                    }
                }
            }
        }
    }

    private fun getDataNames() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.getMatchByNameAsc().collect{
                    when(it){
                        is Result.Success ->{
                            val adapter = TeamAdapter(it.data)
                            binding.rvTeams.layoutManager = LinearLayoutManager(requireContext())
                            binding.rvTeams.adapter = adapter
                        }
                        is Result.Failure ->{
                        }
                    }
                }
            }
        }
    }


}