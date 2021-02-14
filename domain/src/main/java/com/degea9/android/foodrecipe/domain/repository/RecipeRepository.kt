package com.degea9.android.foodrecipe.domain.repository

import com.degea9.android.foodrecipe.domain.model.Recipe

interface RecipeRepository {
    suspend fun getPopularRecipes() : List<Recipe>
}