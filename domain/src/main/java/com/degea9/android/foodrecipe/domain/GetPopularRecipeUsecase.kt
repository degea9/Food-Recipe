package com.degea9.android.foodrecipe.domain

import com.degea9.android.foodrecipe.domain.dispatcher.UseCaseDispatchers
import com.degea9.android.foodrecipe.domain.model.Recipe
import com.degea9.android.foodrecipe.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetPopularRecipeUsecase(
    private val recipeRepository: RecipeRepository,
    private val dispatcherProvider: UseCaseDispatchers
) {

//     operator fun invoke(): Flow<List<Recipe>> {
//        return flow {
//            emit(recipeRepository.getPopularRecipes())
//        }.flowOn(dispatcherProvider.ioDispatcher)
//    }

    fun getRecipes(category:String): Flow<List<Recipe>>{
        return flow {
            emit(recipeRepository.getCategoryRecipes(category))
        }.flowOn(dispatcherProvider.ioDispatcher)
    }
}