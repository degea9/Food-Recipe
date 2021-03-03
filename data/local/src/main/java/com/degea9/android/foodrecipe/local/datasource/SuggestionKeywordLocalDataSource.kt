package com.degea9.android.foodrecipe.local.datasource

import com.degea9.android.foodrecipe.local.dao.SuggestionKeywordDao
import com.degea9.android.foodrecipe.local.entity.SuggestionKeywordEntity

interface SuggestionKeywordLocalDataSource {
    suspend fun insertKeyword(keywordEntity: SuggestionKeywordEntity)

    suspend fun getLocalSuggestionKeyword(): List<SuggestionKeywordEntity>

}

class SuggestionKeywordLocalDataSourceImpl(private val suggestionKeywordDao: SuggestionKeywordDao): SuggestionKeywordLocalDataSource {
    override suspend fun insertKeyword(keywordEntity: SuggestionKeywordEntity) {
        suggestionKeywordDao.insertKeyword(keywordEntity)
    }

    override suspend fun getLocalSuggestionKeyword(): List<SuggestionKeywordEntity> = suggestionKeywordDao.getSuggestionKeyword()
}