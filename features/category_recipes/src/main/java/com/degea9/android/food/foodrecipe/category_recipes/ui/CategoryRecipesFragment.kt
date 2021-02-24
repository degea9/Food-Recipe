package com.degea9.android.food.foodrecipe.category_recipes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
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

    private var category: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            category = it.getString(CATEGORY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryRecipesBinding.inflate(inflater, container, false)
        setup()
        setupObserver()
        return binding.root
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
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            categoryRecipesViewModel.recipePagingDataFlow?.collectLatest {
                pagingController.submitData(it)
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param category category
         * @return A new instance of fragment CategoryRecipesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(category: String) =
            CategoryRecipesFragment().apply {
                arguments = Bundle().apply {
                    putString(CATEGORY, category)
                }
            }
    }
}