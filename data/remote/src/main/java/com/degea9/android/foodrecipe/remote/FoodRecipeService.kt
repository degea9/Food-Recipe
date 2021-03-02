package com.degea9.foodrecipe.remote

import com.degea9.android.foodrecipe.remote.response.SuggestionKeywordResponse
import com.degea9.foodrecipe.remote.response.ListRecipeResponse
import com.degea9.foodrecipe.remote.response.RecipeResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FoodRecipeService {

    /**
     * get recipes by category,the default sort direction is DESC and the default number items each page is 10
     */
    @GET("recipes/complexSearch")
    suspend fun getCategoryRecipes(
        @Query("query") query: String?,
        @Query("type") type: String?,
        @Query("sort") sort: String,
        @Query("sortDirection") sortDirection: String = "desc",
        @Query("offset") offset: Int = 0,
        @Query("number") number: Int = 10,
        @Query("addRecipeInformation") addRecipeInformation: Boolean? = true
    ): ListRecipeResponse

    /**
     * get category recipes,the default sort direction is DESC and the default number items each page is 10
     */
    @GET("recipes/complexSearch")
    suspend fun searchRecipes(
        @Query("query") query: String = "",
        @Query("sort") sort: String?,
        @Query("sortDirection") sortDirection: String = "desc",
        @Query("offset") offset: Int,
        @Query("number") number: Int = 10,
        @Query("addRecipeInformation") addRecipeInformation: Boolean? = true
    ): ListRecipeResponse

    /**
     * get category recipes,the default sort direction is DESC and the default number items each page is 10
     */
    @GET("recipes/{id}/information")
    suspend fun getRecipeDetail(
        @Path("id") id: Int
    ): RecipeResponse

    /**
     * get suggestion keyword when use search
     */
    @GET("recipes/autocomplete")
    suspend fun getSuggestionKeyword(
        @Query("query") query: String,
        @Query("number") number: Int
    ): List<SuggestionKeywordResponse>
}




