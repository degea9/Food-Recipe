package com.degea9.android.foodrecipe.domain

import com.degea9.android.foodrecipe.domain.dispatcher.UseCaseDispatchers
import com.degea9.android.foodrecipe.domain.model.SuggestionKeyword
import com.degea9.android.foodrecipe.domain.repository.RecipeRepository
import com.degea9.android.foodrecipe.domain.repository.SuggestionKeywordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetSuggestionKeywordUseCase @Inject constructor(
    private val suggestionKeywordRepo: SuggestionKeywordRepository,
    private val dispatcherProvider: UseCaseDispatchers
) {
    suspend fun getRemoteSuggestionKeyword(query: String, number: Int):List<SuggestionKeyword> {
        return  suggestionKeywordRepo.getRemoteSuggestionKeyword(query, number)
    }
}