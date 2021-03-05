package com.degea9.android.food.foodrecipe.favorite

import androidx.lifecycle.SavedStateHandle
import com.degea9.android.foodrecipe.core.BaseViewModel
import com.degea9.android.foodrecipe.domain.favorite.GetFavoriteRecipesUseCase
import com.degea9.android.foodrecipe.domain.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteRecipesUseCase: GetFavoriteRecipesUseCase,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    var recipePagingDataFlow : Flow<Recipe>? = null

    init {
        recipePagingDataFlow = getFavoriteRecipesUseCase.getFavoriteRecipes()
    }

}