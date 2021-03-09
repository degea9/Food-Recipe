package com.degea9.android.foodrecipe.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.degea9.android.foodrecipe.local.entity.RecipeEntity

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(recipeEntity: RecipeEntity): Long

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(recipeEntity: RecipeEntity)

    @Transaction
    suspend fun upsert(recipeEntity: RecipeEntity){
        val result = insert(recipeEntity)

        if(result == -1L){
            update(recipeEntity)
        }
    }

    @Query("SELECT * FROM recipes ORDER BY id DESC")
    fun getFavoriteRecipes() : PagingSource<Int,RecipeEntity>

    @Query("SELECT * FROM recipes WHERE id=:id")
    fun getRecipeById(id: Int): RecipeEntity

}