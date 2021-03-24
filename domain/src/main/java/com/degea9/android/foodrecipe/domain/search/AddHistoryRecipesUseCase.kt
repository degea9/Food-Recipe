package com.degea9.android.foodrecipe.domain.search

import com.degea9.android.foodrecipe.domain.model.Recipe
import com.degea9.android.foodrecipe.domain.repository.RecipeRepository
import javax.inject.Inject

class AddHistoryRecipesUseCase @Inject constructor(private val recipeRepository: RecipeRepository) {
    suspend fun addHistoryRecipes(recipe:  Recipe){
        recipeRepository.addHistoryRecipes(recipe)
    }
}