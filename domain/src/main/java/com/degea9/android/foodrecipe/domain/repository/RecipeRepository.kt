package com.degea9.android.foodrecipe.domain.repository

import androidx.paging.PagingData
import com.degea9.android.foodrecipe.domain.model.Recipe
import com.degea9.android.foodrecipe.domain.model.SuggestionKeyword
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun getCategoryRecipes(category: String) : List<Recipe>

    suspend fun getRecipeDetail(id:Int): Recipe

    fun searchRecipe(query:String,sort:String):Flow<PagingData<Recipe>>


    suspend fun addFavorite(recipe: Recipe)

    fun getFavoriteRecipes() : Flow<PagingData<Recipe>>
}