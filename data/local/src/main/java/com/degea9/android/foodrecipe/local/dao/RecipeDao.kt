package com.degea9.android.foodrecipe.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.degea9.android.foodrecipe.local.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(recipeEntity: RecipeEntity): Long

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(recipeEntity: RecipeEntity)

    @Transaction
    fun upsert(recipeEntity: RecipeEntity){
        val result = insert(recipeEntity)

        if(result == -1L){
            update(recipeEntity)
        }
    }

    @Query("SELECT * FROM recipes ORDER BY id DESC")
    fun getFavoriteRecipes() : PagingSource<Int,RecipeEntity>

    @Query("SELECT * FROM recipes WHERE id=:id")
    fun getRecipeById(id: Int): RecipeEntity

    @Query("SELECT * FROM recipes WHERE isHistory = 1")
    fun getHistoryRecipes(): Flow<RecipeEntity>

}