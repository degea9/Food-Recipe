package com.degea9.android.foodrecipe.remote.datasource

import com.degea9.android.foodrecipe.remote.BuildConfig
import com.degea9.foodrecipe.remote.FoodRecipeService
import com.degea9.foodrecipe.remote.response.ListRecipeResponse

//interface for remote data source
interface RecipeRemoteDataSource {
    suspend fun getCategoryRecipes(category:String): ListRecipeResponse
}

class RecipeRemoteDataSourceImpl(private val foodRecipeService: FoodRecipeService) : RecipeRemoteDataSource {

    override suspend fun getCategoryRecipes(category:String): ListRecipeResponse {
        return foodRecipeService.getCategoryRecipes(
            query = "",
            type = "main course",
            sort = category
        )
    }

}