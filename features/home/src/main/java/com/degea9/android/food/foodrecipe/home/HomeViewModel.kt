package com.degea9.android.food.foodrecipe.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.degea9.android.foodrecipe.base.BaseViewModel
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