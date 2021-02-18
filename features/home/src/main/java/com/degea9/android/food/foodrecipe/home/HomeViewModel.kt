package com.degea9.android.food.foodrecipe.home

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.degea9.android.foodrecipe.core.BaseViewModel
import com.degea9.android.foodrecipe.domain.GetPopularRecipeUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getPopularRecipeUsecase: GetPopularRecipeUsecase, private val savedStateHandle: SavedStateHandle):BaseViewModel() {

    fun getRecipe(){
        viewModelScope.launch(Dispatchers.IO) {
            getPopularRecipeUsecase()
        }
    }
}