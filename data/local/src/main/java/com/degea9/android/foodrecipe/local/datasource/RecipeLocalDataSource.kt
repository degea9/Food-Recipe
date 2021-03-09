package com.degea9.android.foodrecipe.local.datasource

import androidx.paging.PagingSource
import com.degea9.android.foodrecipe.local.dao.RecipeDao
import com.degea9.android.foodrecipe.local.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

interface RecipeLocalDataSource {

    suspend fun addFavorite(recipeEntity: RecipeEntity)

    fun getFavoriteRecipes() : PagingSource<Int, RecipeEntity>
}

class RecipeLocalDataSourceImpl (private val recipeDao: RecipeDao): RecipeLocalDataSource{
    override suspend fun addFavorite(recipeEntity: RecipeEntity) {
        recipeDao.upsert(recipeEntity)
    }

    override fun getFavoriteRecipes(): PagingSource<Int, RecipeEntity> = recipeDao.getFavoriteRecipes()

}