package com.degea9.android.foodrecipe.local.datasource

import com.degea9.android.foodrecipe.local.dao.RecipeDao
import com.degea9.android.foodrecipe.local.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

interface RecipeLocalDataSource {

    suspend fun addFavorite(recipeEntity: RecipeEntity)

    fun getFavoriteRecipes() : Flow<RecipeEntity>
}

class RecipeLocalDataSourceImpl (private val recipeDao: RecipeDao): RecipeLocalDataSource{
    override suspend fun addFavorite(recipeEntity: RecipeEntity) {
        recipeDao.addFavorite(recipeEntity)
    }

    override fun getFavoriteRecipes(): Flow<RecipeEntity> = recipeDao.getFavoriteRecipes()

}