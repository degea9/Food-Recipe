package com.degea9.android.foodrecipe.domain.search

import com.degea9.android.foodrecipe.domain.repository.RecipeRepository
import javax.inject.Inject

class InsertHistoryRecipesUseCase @Inject constructor(private val recipeRepository: RecipeRepository) {
    private fun saveRecipeToLocal(recipe: RecipeRepository){
    }
}