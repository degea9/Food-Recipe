package com.degea9.foodrecipe.remote

import com.degea9.foodrecipe.remote.response.ListRecipeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodRecipe {
    @GET("recipes/complexSearch")
    suspend fun getRecipes(
        @Query("apiKey") apiKey: String,
        @Query("query") query: String?,
        @Query("type") type: String?,
        @Query("addRecipeInformation") addRecipeInformation: Boolean? = true
    ): ListRecipeResponse
}