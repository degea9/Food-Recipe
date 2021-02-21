package com.degea9.android.foodrecipe.remote.datasource

import com.degea9.android.foodrecipe.remote.BuildConfig
import com.degea9.foodrecipe.remote.FoodRecipeService
import com.degea9.foodrecipe.remote.response.ListRecipeResponse

//interface for remote data source
interface RecipeRemoteDataSource {
    suspend fun getPopularRecipes(): ListRecipeResponse
}

class RecipeRemoteDataSourceImpl(private val foodRecipeService: FoodRecipeService) : RecipeRemoteDataSource {

    override suspend fun getPopularRecipes(): ListRecipeResponse {
        return foodRecipeService.getPopularRecipe(
            query = "",
            type = "main course"
        )
    }

}