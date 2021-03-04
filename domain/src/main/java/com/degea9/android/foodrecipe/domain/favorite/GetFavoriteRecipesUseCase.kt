package com.degea9.android.foodrecipe.domain.favorite

import com.degea9.android.foodrecipe.domain.dispatcher.UseCaseDispatchers
import com.degea9.android.foodrecipe.domain.model.Recipe
import com.degea9.android.foodrecipe.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetFavoriteRecipesUseCase @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val dispatcherProvider: UseCaseDispatchers
) {

    fun getFavoriteRecipes(): Flow<Recipe>  = recipeRepository.getFavoriteRecipes().flowOn(dispatcherProvider.ioDispatcher)
}