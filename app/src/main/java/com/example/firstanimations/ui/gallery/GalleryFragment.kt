package com.example.firstanimations.ui.gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstanimations.R
import com.example.firstanimations.core.Constants
import com.example.firstanimations.core.Result
import com.example.firstanimations.data.local.AppDatabase
import com.example.firstanimations.databinding.FragmentGalleryBinding
import com.example.firstanimations.presentation.TeamViewModel
import com.example.firstanimations.presentation.TeamViewModelFactory
import com.example.firstanimations.ui.gallery.adapter.TeamAdapter
import kotlinx.coroutines.flow.collect
import kotlin.math.log

class GalleryFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentGalleryBinding
    private val viewModel by viewModels<TeamViewModel> {
        TeamViewModelFactory(
            AppDatabase.getTeamDatabase(
                requireContext()
            ).TeamDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.search.setOnQueryTextListener(this)
        getTeams()
    }

    private fun getTeams() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getTeams().collect {
                    when (it) {
                        is Result.Success -> {
                            val adapter = TeamAdapter(it.data)
                            binding.rvTeams.layoutManager = LinearLayoutManager(requireContext())
                            binding.rvTeams.adapter = adapter
                        }
                        is Result.Failure -> {
                        }
                    }
                }
            }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean = true

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null)
            searchQuery(newText)

        return true
    }

    private fun searchQuery(newText: String) {
        val searchQuery = "%$newText%"
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getTeamsBySearch(searchQuery).collect {
                    when (it) {
                        is Result.Success -> {
                            val adapter = TeamAdapter(it.data)
                            binding.rvTeams.layoutManager = LinearLayoutManager(requireContext())
                            binding.rvTeams.adapter = adapter
                        }
                        is Result.Failure -> {
                            Log.e("getTeams: ", "Error ${it.exception}")

                        }
                    }
                }
            }
        }
    }
}

