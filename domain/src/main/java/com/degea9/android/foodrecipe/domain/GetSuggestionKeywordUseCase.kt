package com.degea9.android.foodrecipe.domain

import com.degea9.android.foodrecipe.domain.dispatcher.UseCaseDispatchers
import com.degea9.android.foodrecipe.domain.model.SuggestionKeyword
import com.degea9.android.foodrecipe.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetSuggestionKeywordUseCase @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val dispatcherProvider: UseCaseDispatchers
) {
    suspend fun getSuggestionKeyword(query: String, number: Int):List<SuggestionKeyword> {
        return  recipeRepository.getSuggestionKeyword(query, number)
    }
}