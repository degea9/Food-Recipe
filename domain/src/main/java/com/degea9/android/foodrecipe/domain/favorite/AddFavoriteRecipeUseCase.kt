package com.degea9.android.foodrecipe.domain.favorite

import com.degea9.android.foodrecipe.domain.dispatcher.UseCaseDispatchers
import com.degea9.android.foodrecipe.domain.model.Recipe
import com.degea9.android.foodrecipe.domain.repository.RecipeRepository
import javax.inject.Inject

class AddFavoriteRecipeUseCase @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val dispatcherProvider: UseCaseDispatchers
) {

    suspend fun addFavoriteRecipe(recipe:Recipe) = recipeRepository.addFavorite(recipe)
}