package com.degea9.android.foodrecipe.domain.repository

import com.degea9.android.foodrecipe.domain.model.SuggestionKeyword
import kotlinx.coroutines.flow.Flow

interface SuggestionKeywordRepository {
    suspend fun getRemoteSuggestionKeyword(query: String, number: Int): List<SuggestionKeyword>

    fun getLocalSuggestionKeyword(): Flow<List<SuggestionKeyword>>

    fun saveSuggestionKeyword(suggestion: SuggestionKeyword)
}