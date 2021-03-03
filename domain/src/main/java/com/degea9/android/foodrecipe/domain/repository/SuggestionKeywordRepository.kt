package com.degea9.android.foodrecipe.domain.repository

import com.degea9.android.foodrecipe.domain.model.SuggestionKeyword

interface SuggestionKeywordRepository {
    suspend fun getRemoteSuggestionKeyword(query: String, number: Int): List<SuggestionKeyword>

    suspend fun getLocalSuggestionKeyword(): List<SuggestionKeyword>

    suspend fun saveSuggestionKeyword(suggestion: SuggestionKeyword)
}