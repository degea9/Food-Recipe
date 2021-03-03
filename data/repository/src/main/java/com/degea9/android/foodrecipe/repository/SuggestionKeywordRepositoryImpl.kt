package com.degea9.android.foodrecipe.repository

import com.degea9.android.foodrecipe.domain.model.SuggestionKeyword
import com.degea9.android.foodrecipe.domain.repository.SuggestionKeywordRepository
import com.degea9.android.foodrecipe.local.datasource.SuggestionKeywordLocalDataSource
import com.degea9.android.foodrecipe.remote.datasource.SuggestionKeywordRemoteDataSource
import com.degea9.android.foodrecipe.repository.mapper.SuggestionKeywordDataListMapper
import com.degea9.android.foodrecipe.repository.mapper.local.LocalSuggestionKeywordDataListMapper
import javax.inject.Inject

class SuggestionKeywordRepositoryImpl @Inject constructor(private val suggestionKeywordLocalDataSource: SuggestionKeywordLocalDataSource,
                                                          private val suggestionKeywordRemoteDataSource: SuggestionKeywordRemoteDataSource,
                                                          private val suggestionKeyWordListMapper: SuggestionKeywordDataListMapper,
                                                          private val localSuggestionKeywordDataListMapper: LocalSuggestionKeywordDataListMapper
): SuggestionKeywordRepository {
    override suspend fun getRemoteSuggestionKeyword(query: String, number: Int): List<SuggestionKeyword> {
        return suggestionKeyWordListMapper.map(suggestionKeywordRemoteDataSource.getSuggestionKeyword(query, number)).orEmpty()
    }

    override suspend fun getLocalSuggestionKeyword(): List<SuggestionKeyword> {
        return localSuggestionKeywordDataListMapper.map(suggestionKeywordLocalDataSource.getLocalSuggestionKeyword()).orEmpty()
    }

    override suspend fun saveSuggestionKeyword(suggestion: SuggestionKeyword) {
        //do something
    }

}