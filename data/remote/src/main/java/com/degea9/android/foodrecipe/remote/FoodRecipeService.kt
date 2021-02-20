package com.degea9.foodrecipe.remote

import com.degea9.android.foodrecipe.remote.BuildConfig
import com.degea9.foodrecipe.remote.response.ListRecipeResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodRecipeService {

    @GET("recipes/complexSearch")
    suspend fun getRecipes(
        @Query("apiKey") apiKey: String,
        @Query("query") query: String?,
        @Query("type") type: String?,
        @Query("addRecipeInformation") addRecipeInformation: Boolean? = true
    ): ListRecipeResponse

    /**
     * get popular recipes,the default sort direction is DESC and the default number items each page is 10
     */
    @GET("recipes/complexSearch")
    suspend fun getPopularRecipe(
        @Query("apiKey") apiKey: String,
        @Query("query") query: String?,
        @Query("type") type: String?,
        @Query("sort") sort: String? = "popularity",
        @Query("sortDirection") sortDirection:String = "desc",
        @Query("offset") offset:Int = 0,
        @Query("number") number:Int = 10,
        @Query("addRecipeInformation") addRecipeInformation: Boolean? = true
    ): ListRecipeResponse

    /**
     * get category recipes,the default sort direction is DESC and the default number items each page is 10
     */
    @GET("recipes/complexSearch")
    suspend fun searchRecipes(
        @Query("apiKey") apiKey: String,
        @Query("query") query: String = "",
        @Query("sort") sort: String?,
        @Query("sortDirection") sortDirection:String = "desc",
        @Query("offset") offset:Int,
        @Query("number") number:Int = 10,
        @Query("addRecipeInformation") addRecipeInformation: Boolean? = true
    ): ListRecipeResponse
}




