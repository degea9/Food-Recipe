package com.degea9.android.foodrecipe.repository

import android.util.Log
import com.degea9.android.foodrecipe.domain.model.Recipe
import com.degea9.android.foodrecipe.domain.repository.RecipeRepository
import com.degea9.android.foodrecipe.remote.datasource.RecipeRemoteDataSource
import com.degea9.android.foodrecipe.repository.mapper.RecipeDataListMapper
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(private val recipeRemoteDataSource: RecipeRemoteDataSource, private val mapper:RecipeDataListMapper) :RecipeRepository{
    override suspend fun getPopularRecipes(): List<Recipe> {
        Log.e("tuandang","RecipeRepositoryImpl getPopularRecipes")
        return mapper.map(recipeRemoteDataSource.getPopularRecipes().results).orEmpty()
    }
}