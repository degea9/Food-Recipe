package com.degea9.android.foodrecipe.local.dao

import android.util.Log
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
    suspend fun getRecipeById(id: Int): RecipeEntity?

    @Query("SELECT * FROM recipes WHERE isHistory = 1")
    fun getHistoryRecipes(): Flow<List<RecipeEntity>>

    @Transaction
    suspend fun addHistory(recipeEntity: RecipeEntity){
        try{
            val result = getRecipeById(recipeEntity.id)
            if(result==null){
                recipeEntity.isHistory = true
                upsert(recipeEntity)
            }
            else {
                result.isHistory = true
                upsert(result)
            }
        }
        catch (t: Throwable){
            Log.d("ADDHISTORY ",t.toString())
        }

    }

}