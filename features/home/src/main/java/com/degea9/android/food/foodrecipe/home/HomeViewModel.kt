package com.degea9.android.food.foodrecipe.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.degea9.android.foodrecipe.core.BaseViewModel
import com.degea9.android.foodrecipe.domain.GetPopularRecipeUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @ViewModelInject constructor(private val getPopularRecipeUsecase: GetPopularRecipeUsecase):BaseViewModel() {

    fun getRecipe(){
        viewModelScope.launch {
            getPopularRecipeUsecase()
        }
    }
}