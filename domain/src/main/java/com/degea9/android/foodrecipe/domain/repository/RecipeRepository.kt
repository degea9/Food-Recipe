package com.degea9.android.foodrecipe.domain.repository

import androidx.paging.PagingData
import com.degea9.android.foodrecipe.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun getPopularRecipes() : List<Recipe>

    fun searchRecipe(query:String,sort:String):Flow<PagingData<Recipe>>

}