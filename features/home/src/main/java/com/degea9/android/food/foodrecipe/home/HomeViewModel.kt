package com.degea9.android.food.foodrecipe.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.degea9.android.foodrecipe.base.BaseViewModel
//import com.degea9.android.foodrecipe.remote.BuildConfig
//import com.degea9.foodrecipe.remote.FoodRecipeApi
import kotlinx.coroutines.launch

class HomeViewModel:BaseViewModel() {

    fun getRecipe(){
        viewModelScope.launch {
//             val listRecipeResponse = FoodRecipeApi.retrofitService.getRecipes(
//                BuildConfig.SPOONACULAR_API_KEY,
//                "pho",
//                "main course"
//            )
//            Log.d("HomeViewModel","listRecipeResponse $listRecipeResponse")
        }
    }
}