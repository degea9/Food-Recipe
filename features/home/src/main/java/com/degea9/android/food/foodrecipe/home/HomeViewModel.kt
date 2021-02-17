package com.degea9.android.food.foodrecipe.home

import androidx.lifecycle.viewModelScope
import com.degea9.android.foodrecipe.core.BaseViewModel
import com.degea9.android.foodrecipe.domain.GetPopularRecipeUsecase
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val getPopularRecipeUsecase: GetPopularRecipeUsecase):BaseViewModel() {

    fun getRecipe(){
        viewModelScope.launch {
            getPopularRecipeUsecase()
        }
    }
}