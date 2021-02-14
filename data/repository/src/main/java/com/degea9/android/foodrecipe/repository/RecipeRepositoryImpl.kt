package com.degea9.android.foodrecipe.repository

import com.degea9.android.foodrecipe.domain.model.Recipe
import com.degea9.android.foodrecipe.domain.repository.RecipeRepository
import com.degea9.android.foodrecipe.remote.datasource.RecipeRemoteDataSource

class RecipeRepositoryImpl(private val recipeRemoteDataSource: RecipeRemoteDataSource) :RecipeRepository{
    override suspend fun getPopularRecipes(): List<Recipe> {
        return recipeRemoteDataSource.getPopularRecipes()
    }
}