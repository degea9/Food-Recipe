package com.degea9.android.food.foodrecipe.model

import com.degea9.android.foodrecipe.domain.model.Recipe

data class CategoryRecipes(val category:String,val recipes: List<Recipe>)