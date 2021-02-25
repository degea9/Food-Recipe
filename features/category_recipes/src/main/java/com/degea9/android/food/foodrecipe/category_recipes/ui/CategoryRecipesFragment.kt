package com.degea9.android.food.foodrecipe.category_recipes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import com.degea9.android.food.foodrecipe.category_recipes.databinding.FragmentCategoryRecipesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

private const val CATEGORY = "category"

@AndroidEntryPoint
class CategoryRecipesFragment : Fragment() {

    private val categoryRecipesViewModel: CategoryRecipesViewModel by viewModels()
    private val pagingController = RecipePagingController()
    private lateinit var binding:FragmentCategoryRecipesBinding

    val args: CategoryRecipesFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryRecipesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        setupObserver()
    }

    private fun setup(){
        binding.rvRecipes.adapter = pagingController.adapter
        binding.rvRecipes.setItemSpacingPx(30)
        setupObserver()
        pagingController.addLoadStateListener {loadState->
            Timber.d("get category recipes loadState $loadState")
            // Only show the list if refresh succeeds.
            binding.rvRecipes.isVisible = loadState.source.refresh is LoadState.NotLoading
            // Show loading spinner during initial load or refresh.
            binding.progressBar.isVisible = loadState.append is LoadState.Loading
            if(loadState.source.refresh is LoadState.Loading){
                binding.shimmerViewContainer.startShimmer()
                binding.shimmerViewContainer.isVisible = true
            }else{
                binding.shimmerViewContainer.stopShimmer()
                binding.shimmerViewContainer.isVisible = false
            }
        }
        Timber.d("category is ${args.category}")
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            categoryRecipesViewModel.recipePagingDataFlow?.collectLatest {
                pagingController.submitData(it)
            }
        }
    }
}