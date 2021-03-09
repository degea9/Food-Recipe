package com.degea9.android.foodrecipe.repository

import com.degea9.android.foodrecipe.domain.model.SuggestionKeyword
import com.degea9.android.foodrecipe.domain.repository.SuggestionKeywordRepository
import com.degea9.android.foodrecipe.local.datasource.SuggestionKeywordLocalDataSource
import com.degea9.android.foodrecipe.remote.datasource.SuggestionKeywordRemoteDataSource
import com.degea9.android.foodrecipe.repository.mapper.DataMappersFacade
import com.degea9.android.foodrecipe.repository.mapper.SuggestionKeywordDataListMapper
import com.degea9.android.foodrecipe.repository.mapper.local.LocalSuggestionKeywordDataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SuggestionKeywordRepositoryImpl @Inject constructor(private val suggestionKeywordLocalDataSource: SuggestionKeywordLocalDataSource,
                                                          private val suggestionKeywordRemoteDataSource: SuggestionKeywordRemoteDataSource,
                                                          private val mapper: DataMappersFacade
): SuggestionKeywordRepository {
    override suspend fun getRemoteSuggestionKeyword(query: String, number: Int): List<SuggestionKeyword> {
        return mapper.mapRemoteSuggestionKeywordListToDomain(suggestionKeywordRemoteDataSource.getSuggestionKeyword(query, number)).orEmpty()
    }

    override fun getLocalSuggestionKeyword(): Flow<SuggestionKeyword> {
        return suggestionKeywordLocalDataSource.getLocalSuggestionKeyword().map {
            mapper.mapLocalSuggestionKeywordToDomain(it)
        }
    }

    override fun saveSuggestionKeyword(suggestion: SuggestionKeyword) {
        mapper.mapDomainSuggestionKeywordToLocal(suggestion)?.let {
            suggestionKeywordLocalDataSource.insertKeyword(it)
        }
    }

}