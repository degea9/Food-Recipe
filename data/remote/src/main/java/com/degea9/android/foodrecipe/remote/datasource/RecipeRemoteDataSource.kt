package com.degea9.android.foodrecipe.remote.datasource

import com.degea9.android.foodrecipe.remote.BuildConfig
import com.degea9.foodrecipe.remote.FoodRecipeService
import com.degea9.foodrecipe.remote.response.ListRecipeResponse
import com.degea9.foodrecipe.remote.response.RecipeResponse

//interface for remote data source
interface RecipeRemoteDataSource {
    suspend fun getCategoryRecipes(category:String): ListRecipeResponse

    suspend fun getRecipeDetail(id:Int) : RecipeResponse
}

class RecipeRemoteDataSourceImpl(private val foodRecipeService: FoodRecipeService) : RecipeRemoteDataSource {

    override suspend fun getCategoryRecipes(category:String): ListRecipeResponse {
        return foodRecipeService.getCategoryRecipes(
            query = "",
            type = "main course",
            sort = category
        )
    }

    override suspend fun getRecipeDetail(id: Int): RecipeResponse {
        return foodRecipeService.getRecipeDetail(id)
    }

}