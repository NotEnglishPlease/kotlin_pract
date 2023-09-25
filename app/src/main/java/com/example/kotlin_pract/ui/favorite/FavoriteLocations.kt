package com.example.kotlin_pract.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.kotlin_pract.R

class FavoriteLocations : Fragment() {

    private val viewModel: FavoriteLocationsViewModel by viewModels { FavoriteLocationsViewModel.Factory }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_locations, container, false)
    }

}