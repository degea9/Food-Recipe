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
}

private val httpLogginInterceptor =
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

private val okHttpClient = OkHttpClient.Builder()

private val retrofit = Retrofit.Builder()
    .client(
        okHttpClient.addInterceptor(
            httpLogginInterceptor
        ).build()
    )
    .addConverterFactory(MoshiConverterFactory.create())
    .baseUrl(BuildConfig.BASE_URL_API)
    .build()

object FoodRecipeApi {
    val retrofitService: FoodRecipeService by lazy {
        retrofit.create(FoodRecipeService::class.java)
    }
}