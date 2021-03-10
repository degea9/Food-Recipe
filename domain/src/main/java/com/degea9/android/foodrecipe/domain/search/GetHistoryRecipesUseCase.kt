package com.degea9.android.foodrecipe.domain.search

import com.degea9.android.foodrecipe.domain.model.Recipe
import com.degea9.android.foodrecipe.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHistoryRecipesUseCase @Inject constructor(
        private val recipesRepository: RecipeRepository
) {
    fun getHistoryRecipes(): Flow<List<Recipe>>{
        return recipesRepository.getHistoryRecipes()
    }
}