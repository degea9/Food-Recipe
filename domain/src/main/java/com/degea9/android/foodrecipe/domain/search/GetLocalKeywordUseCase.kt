package com.degea9.android.foodrecipe.domain.search

import com.degea9.android.foodrecipe.domain.model.SuggestionKeyword
import com.degea9.android.foodrecipe.domain.repository.SuggestionKeywordRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalKeywordUseCase @Inject constructor(
    private val suggestionKeywordRepo: SuggestionKeywordRepository,
) {
    fun getLocalSuggestionKeyword():Flow<SuggestionKeyword> {
        return  suggestionKeywordRepo.getLocalSuggestionKeyword()
    }
}