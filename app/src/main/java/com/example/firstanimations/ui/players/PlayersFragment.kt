package com.example.firstanimations.ui.players

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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstanimations.R
import com.example.firstanimations.core.Constants
import com.example.firstanimations.core.Result
import com.example.firstanimations.data.local.AppDatabase
import com.example.firstanimations.databinding.FragmentPlayersBinding
import com.example.firstanimations.presentation.PlayerViewModel
import com.example.firstanimations.presentation.PlayerViewModelFactory
import com.example.firstanimations.ui.players.adapter.PlayersAdapter
import kotlinx.coroutines.flow.collect


class PlayersFragment : Fragment(R.layout.fragment_players) {
    private val args by navArgs<PlayersFragmentArgs>()
    private lateinit var binding : FragmentPlayersBinding
    private val viewModel by viewModels<PlayerViewModel> { PlayerViewModelFactory(AppDatabase.getPlayerDatabase(requireContext()).PlayerDao())  }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPlayersBinding.bind(view)
        setFlag()
        getPlayers()
    }

    private fun setFlag() {
        Constants.APPBAR_BINDING!!.imgFlag.visibility = View.VISIBLE
    }

    private fun getPlayers() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.getPlayersOrderByJersey(args.idTeam).collect{
                    when(it){
                        is Result.Success ->{
                            val adapter = PlayersAdapter(it.data)
                            binding.rvPlayers.layoutManager = LinearLayoutManager(requireContext())
                            binding.rvPlayers.adapter=  adapter
                        }
                        is Result.Failure ->{}
                    }
                }
            }
        }
    }

}