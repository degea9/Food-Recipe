package com.degea9.android.food.foodrecipe.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.degea9.android.foodrecipe.core.BaseViewModel
import com.degea9.android.foodrecipe.domain.GetPopularRecipeUsecase
import com.degea9.android.foodrecipe.domain.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularRecipeUsecase: GetPopularRecipeUsecase,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    val popularRecipes : LiveData<List<Recipe>> =  getPopularRecipeUsecase().asLiveData(viewModelScope.coroutineContext+Dispatchers.IO)


}