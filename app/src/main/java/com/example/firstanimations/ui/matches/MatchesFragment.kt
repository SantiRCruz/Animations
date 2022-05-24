package com.example.firstanimations.ui.matches

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstanimations.R
import com.example.firstanimations.core.Constants
import com.example.firstanimations.core.Result
import com.example.firstanimations.data.local.AppDatabase
import com.example.firstanimations.databinding.FragmentMatchesBinding
import com.example.firstanimations.presentation.MatchViewModel
import com.example.firstanimations.presentation.MatchViewModelFactory
import com.example.firstanimations.ui.matches.adapter.MatchesAdapter
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.flow.collect
import java.lang.String.format
import java.text.SimpleDateFormat
import java.util.*


class MatchesFragment : Fragment(R.layout.fragment_matches) {
  private lateinit var binding : FragmentMatchesBinding
  private val viewModel by viewModels<MatchViewModel> { MatchViewModelFactory(AppDatabase.getMatchDatabase(requireContext()).MatchDao()) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMatchesBinding.bind(view)

        getDataByDate(getActualDate())
        clicks()

    }

    private fun clicks() {
        binding.txtDate.setOnClickListener { openCalendar() }
    }

    private fun openCalendar() {
        val c = Calendar.getInstance()
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select the date you want to filter the matches")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()

        datePicker.show(childFragmentManager,"datePicker")
        datePicker.addOnPositiveButtonClickListener {
            val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
            c.time = simpleDateFormat.parse(simpleDateFormat.format(Date(it)))
            c.add(Calendar.DATE,1)
            binding.txtDate.text = simpleDateFormat.format(c.time)
            getDataByDate(simpleDateFormat.format(c.time))
        }
        datePicker.addOnNegativeButtonClickListener {
             getMatches()
            binding.txtDate.text = "You don't select any date"
        }
    }

    private fun getMatches(){
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.fetchMatches().collect {
                    when(it){
                        is Result.Success -> {
                            val adapter = MatchesAdapter(it.data)
                            binding.rvMatches.layoutManager = LinearLayoutManager(requireContext())
                            binding.rvMatches.adapter= adapter
                        }
                    }
                }
            }
        }
    }
    private fun getDataByDate(date:String) {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.fetchMatchesByDate(date).collect {
                    when(it){
                        is Result.Success -> {
                            val adapter = MatchesAdapter(it.data)
                            binding.rvMatches.layoutManager = LinearLayoutManager(requireContext())
                            binding.rvMatches.adapter= adapter
                        }
                    }
                }
            }
        }
    }
    private fun getActualDate():String{
        val date = SimpleDateFormat("dd-MM-yyyy")
        binding.txtDate.text = date.format(Date())
        return date.format(Date())
    }

}