package com.degea9.android.foodrecipe.local.datasource

import com.degea9.android.foodrecipe.local.dao.SuggestionKeywordDao
import com.degea9.android.foodrecipe.local.entity.SuggestionKeywordEntity
import kotlinx.coroutines.flow.Flow

interface SuggestionKeywordLocalDataSource {
    suspend fun insertKeyword(keywordEntity: SuggestionKeywordEntity)

    fun getLocalSuggestionKeyword(): Flow<SuggestionKeywordEntity>

}

class SuggestionKeywordLocalDataSourceImpl(private val suggestionKeywordDao: SuggestionKeywordDao): SuggestionKeywordLocalDataSource {
    override suspend fun insertKeyword(keywordEntity: SuggestionKeywordEntity) {
        suggestionKeywordDao.insertKeyword(keywordEntity)
    }

    override fun getLocalSuggestionKeyword(): Flow<SuggestionKeywordEntity> = suggestionKeywordDao.getSuggestionKeyword()
}