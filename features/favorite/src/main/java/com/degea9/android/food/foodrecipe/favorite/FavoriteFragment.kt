package com.degea9.android.food.foodrecipe.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.degea9.android.food.foodrecipe.favorite.databinding.FragmentFavoriteBinding
import com.degea9.android.foodrecipe.domain.model.Recipe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding

    private val pagingController = FavoriteRecipePagingController(::onItemClick)

    private val favoriteViewModel: FavoriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        setupObserver()
    }

    private fun setup(){
        val layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT)
        pagingController.spanCount = SPAN_COUNT
        //binding.rvRecipes.setItemSpacingDp(16)
        layoutManager.spanSizeLookup = pagingController.spanSizeLookup
        binding.rvRecipes.layoutManager = layoutManager
        binding.rvRecipes.adapter = pagingController.adapter
    }

    private fun setupObserver(){
        viewLifecycleOwner.lifecycleScope.launch {
            favoriteViewModel.recipePagingDataFlow?.collectLatest {
                pagingController.submitData(it)
            }
        }
    }

    private fun onItemClick(recipe: Recipe?){
        recipe?.let {
            findNavController().navigate(FavoriteFragmentDirections.actionFavoriteFragmentToRecipeDetailFragment(it.id))
        }
    }

    companion object {
        private const val SPAN_COUNT = 3
    }
}