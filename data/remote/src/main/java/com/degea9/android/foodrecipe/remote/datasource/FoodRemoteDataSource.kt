package com.degea9.android.foodrecipe.remote.datasource

import android.util.Log
import com.degea9.android.foodrecipe.remote.response.ImageAnalysisResponse
import com.degea9.foodrecipe.remote.FoodRecipeService
import okhttp3.MultipartBody

//interface for remote data source
interface FoodRemoteDataSource {
    suspend fun scanImage(image: MultipartBody.Part): ImageAnalysisResponse
}

class FoodRemoteDataSourceImpl(private val foodRecipeService: FoodRecipeService) : FoodRemoteDataSource {
    override suspend fun scanImage(image: MultipartBody.Part): ImageAnalysisResponse {
        return foodRecipeService.scanImage(image)
    }
}