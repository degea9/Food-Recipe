package com.degea9.android.foodrecipe.remote.datasource

import com.degea9.android.foodrecipe.remote.BuildConfig
import com.degea9.android.foodrecipe.remote.response.SuggestionKeywordResponse
import com.degea9.foodrecipe.remote.FoodRecipeService
import com.degea9.foodrecipe.remote.response.ListRecipeResponse
import com.degea9.foodrecipe.remote.response.RecipeResponse

//interface for remote data source
interface RecipeRemoteDataSource {
    suspend fun getCategoryRecipes(category:String): ListRecipeResponse

    suspend fun getRecipeDetail(id:Int) : RecipeResponse

    suspend fun getSuggestionKeyword(query: String, number: Int): List<SuggestionKeywordResponse>
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

    override suspend fun getSuggestionKeyword(
        query: String,
        number: Int
    ): List<SuggestionKeywordResponse> {
        return foodRecipeService.getSuggestionKeyword(query, number)
    }

}