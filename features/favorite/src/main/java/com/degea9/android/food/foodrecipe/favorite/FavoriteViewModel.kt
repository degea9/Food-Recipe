package com.degea9.android.food.foodrecipe.favorite

import androidx.lifecycle.SavedStateHandle
import com.degea9.android.foodrecipe.core.BaseViewModel
import com.degea9.android.foodrecipe.domain.favorite.GetFavoriteRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteRecipesUseCase: GetFavoriteRecipesUseCase,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

}