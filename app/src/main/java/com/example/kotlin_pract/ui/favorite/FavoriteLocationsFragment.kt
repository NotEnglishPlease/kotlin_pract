package com.example.kotlin_pract.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_pract.R
import com.example.kotlin_pract.data.db.toCityUi
import com.example.kotlin_pract.databinding.FragmentFavoriteLocationsBinding

class FavoriteLocationsFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteLocationsBinding

    private val viewModel: FavoriteLocationsViewModel by viewModels { FavoriteLocationsViewModel.Factory }
    private lateinit var adapter: FavoriteLocationsAdapter
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
        adapter = FavoriteLocationsAdapter(
            onFavoriteButtonClick = viewModel::setFavoriteCity,
            favoriteIcon = requireContext().getDrawable(R.drawable.baseline_favorite_24),
            notFavoriteIcon = requireContext().getDrawable(R.drawable.baseline_favorite_border_24)
        )

        viewModel.cities.observe(viewLifecycleOwner) { value ->
            adapter.submitList(value.map { it.toCityUi() })
            adapter.notifyDataSetChanged()
        }
        binding.favoriteCitiesRecyclerView.adapter = adapter
        binding.favoriteCitiesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}