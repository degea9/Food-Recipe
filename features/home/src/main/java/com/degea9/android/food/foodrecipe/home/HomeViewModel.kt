package com.degea9.android.food.foodrecipe.home

import androidx.lifecycle.viewModelScope
import com.degea9.android.foodrecipe.core.BaseViewModel
import com.degea9.android.foodrecipe.domain.GetPopularRecipeUsecase
//import com.degea9.android.foodrecipe.remote.BuildConfig
//import com.degea9.foodrecipe.remote.FoodRecipeApi
import kotlinx.coroutines.launch

class HomeViewModel(private val getPopularRecipeUsecase: GetPopularRecipeUsecase):BaseViewModel() {

    fun getRecipe(){
        viewModelScope.launch {
            getPopularRecipeUsecase()
        }
    }
}