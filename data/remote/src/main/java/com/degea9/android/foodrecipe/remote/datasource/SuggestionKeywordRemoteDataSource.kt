package com.degea9.android.foodrecipe.remote.datasource

import com.degea9.android.foodrecipe.remote.response.SuggestionKeywordResponse
import com.degea9.foodrecipe.remote.FoodRecipeService
import javax.inject.Inject

interface SuggestionKeywordRemoteDataSource {
    suspend fun getSuggestionKeyword(query: String, number: Int): List<SuggestionKeywordResponse>

}

class SuggestionKeywordRemoteDataSourceImpl @Inject constructor(private val foodRecipeService: FoodRecipeService) : SuggestionKeywordRemoteDataSource {

    override suspend fun getSuggestionKeyword(
        query: String,
        number: Int
    ): List<SuggestionKeywordResponse> {
        return foodRecipeService.getSuggestionKeyword(query, number)
    }

}