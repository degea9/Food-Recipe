package com.degea9.android.food.foodrecipe.category_recipes.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.degea9.android.foodrecipe.core.BaseViewModel
import com.degea9.android.foodrecipe.domain.SearchRecipeUsecase
import com.degea9.android.foodrecipe.domain.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CategoryRecipesViewModel @Inject constructor(
    private val searchRecipeUsecase: SearchRecipeUsecase,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    /**
     * search recipe by sorting option(category) such as popularity,healthiness,price...
     */
    fun searchRecipe(sort: String): Flow<PagingData<Recipe>> = searchRecipeUsecase.searchRecipe(query = "",sort=sort).cachedIn(viewModelScope)
}