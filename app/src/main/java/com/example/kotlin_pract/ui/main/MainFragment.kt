package com.example.kotlin_pract.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.kotlin_pract.R
import com.example.kotlin_pract.databinding.FragmentMainBinding
import com.example.kotlin_pract.ui.favorite.FavoriteLocationsFragment


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    val viewModel: MainViewModel by viewModels { MainViewModel.Factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addCityButton.setOnClickListener {
            val newCity = binding.textInputEditText.text.toString().trim()
            if (newCity.isNotEmpty()) {
                viewModel.addCity(newCity)
            }
        }

        binding.buttonToFavourite.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.container, FavoriteLocationsFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        binding.buttonToWeek.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_weekForecast)
        }
    }
}