package com.example.firstanimations.ui.matches

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.firstanimations.R
import com.example.firstanimations.databinding.FragmentMatchesBinding


class MatchesFragment : Fragment(R.layout.fragment_matches) {
  private lateinit var binding : FragmentMatchesBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMatchesBinding.bind(view)

    }
}