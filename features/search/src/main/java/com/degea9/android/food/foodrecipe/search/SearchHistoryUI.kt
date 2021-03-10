package com.degea9.android.food.foodrecipe.search

import com.degea9.android.foodrecipe.domain.model.Recipe
import com.degea9.android.foodrecipe.domain.model.SuggestionKeyword

data class SearchHistoryUI(
        val recipes: List<Recipe>,
        val suggestionKeywords: List<SuggestionKeyword>
)