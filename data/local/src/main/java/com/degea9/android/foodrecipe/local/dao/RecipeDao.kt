package com.degea9.android.foodrecipe.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.degea9.android.foodrecipe.local.entity.RecipeEntity

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(recipeEntity: RecipeEntity)

    @Query("SELECT * FROM recipes ORDER BY id DESC")
    fun getFavoriteRecipes() : PagingSource<Int,RecipeEntity>

}