package com.degea9.android.foodrecipe.domain

import com.degea9.android.foodrecipe.domain.dispatcher.UseCaseDispatchers
import com.degea9.android.foodrecipe.domain.model.Recipe
import com.degea9.android.foodrecipe.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetRecipeDetailUseCase @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val dispatcherProvider: UseCaseDispatchers
) {

    fun getRecipesDetail(id:Int): Flow<Recipe> {
        return flow {
            emit(recipeRepository.getRecipeDetail(id))
        }.flowOn(dispatcherProvider.ioDispatcher)
    }
}