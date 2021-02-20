package com.degea9.android.foodrecipe.domain

import androidx.paging.PagingData
import com.degea9.android.foodrecipe.domain.dispatcher.UseCaseDispatchers
import com.degea9.android.foodrecipe.domain.model.Recipe
import com.degea9.android.foodrecipe.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class SearchRecipeUsecase(
    private val recipeRepository: RecipeRepository,
    private val dispatcherProvider: UseCaseDispatchers
) {

    fun searchRecipe(query:String,sort:String): Flow<PagingData<Recipe>> = recipeRepository.searchRecipe(query=query,sort=sort).flowOn(dispatcherProvider.ioDispatcher)
}