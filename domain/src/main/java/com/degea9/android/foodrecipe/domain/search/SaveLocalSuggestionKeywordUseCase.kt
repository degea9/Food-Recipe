package com.degea9.android.foodrecipe.domain.search

import com.degea9.android.foodrecipe.domain.model.SuggestionKeyword
import com.degea9.android.foodrecipe.domain.repository.RecipeRepository
import com.degea9.android.foodrecipe.domain.repository.SuggestionKeywordRepository
import javax.inject.Inject

class SaveLocalSuggestionKeywordUseCase @Inject constructor(private val suggestionKeywordRepository: SuggestionKeywordRepository) {
    fun saveKeyword(suggestionKeyword: SuggestionKeyword){
        suggestionKeywordRepository.saveSuggestionKeyword(suggestionKeyword)
    }
}