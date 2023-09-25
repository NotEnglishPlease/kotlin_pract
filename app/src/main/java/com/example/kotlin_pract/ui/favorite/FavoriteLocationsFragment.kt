package com.example.kotlin_pract.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.kotlin_pract.databinding.FragmentFavoriteLocationsBinding

class FavoriteLocationsFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteLocationsBinding

    private val viewModel: FavoriteLocationsViewModel by viewModels { FavoriteLocationsViewModel.Factory }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteLocationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}