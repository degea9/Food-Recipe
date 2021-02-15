package com.degea9.android.foodrecipe.repository

import com.degea9.android.foodrecipe.domain.model.Recipe
import com.degea9.android.foodrecipe.domain.repository.RecipeRepository
import com.degea9.android.foodrecipe.remote.datasource.RecipeRemoteDataSource
import com.degea9.android.foodrecipe.repository.mapper.RecipeDataListMapper

class RecipeRepositoryImpl(private val recipeRemoteDataSource: RecipeRemoteDataSource,private val mapper:RecipeDataListMapper) :RecipeRepository{
    override suspend fun getPopularRecipes(): List<Recipe> {
        return mapper.map(recipeRemoteDataSource.getPopularRecipes().results).orEmpty()
    }
}