package com.degea9.android.food.foodrecipe.category_recipes.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.degea9.android.food.foodrecipe.category_recipes.R
import com.degea9.android.foodrecipe.core.BaseActivity
import com.degea9.android.foodrecipe.domain.model.Recipe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_category_recipes.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

@AndroidEntryPoint
class CategoryRecipesActivity : BaseActivity() {

    private val categoryRecipesViewModel: CategoryRecipesViewModel by viewModels()
    private val pagingController = RecipePagingController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_recipes)
        rv_recipes.adapter = pagingController.adapter
        rv_recipes.setItemSpacingPx(30)
        setupObserver()
        pagingController.addLoadStateListener {loadState->
            Timber.d("get category recipes loadState $loadState")
            // Only show the list if refresh succeeds.
            rv_recipes.isVisible = loadState.source.refresh is LoadState.NotLoading
            // Show loading spinner during initial load or refresh.
            progress_bar.isVisible = loadState.append is LoadState.Loading
            if(loadState.source.refresh is LoadState.Loading){
                shimmer_view_container.startShimmer()
                shimmer_view_container.isVisible = true
            }else{
                shimmer_view_container.stopShimmer()
                shimmer_view_container.isVisible = false
            }
        }
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            categoryRecipesViewModel.searchRecipe("popularity").collectLatest {
                pagingController.submitData(it)
            }
        }
    }

}