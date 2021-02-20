package com.degea9.android.food.foodrecipe.category_recipes.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.degea9.android.food.foodrecipe.category_recipes.R
import com.degea9.android.foodrecipe.core.BaseActivity
import com.degea9.android.foodrecipe.domain.model.Recipe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_category_recipes.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class CategoryRecipesActivity : BaseActivity() {

    private val categoryRecipesViewModel: CategoryRecipesViewModel by viewModels()
    private val adapter = RecipesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_recipes)
        rv_recipes.adapter = adapter
        setupObserver()
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            categoryRecipesViewModel.searchRecipe("popularity").collectLatest {
                Timber.d("get category recipes result ")
                adapter.submitData(it)
            }
        }
    }

}