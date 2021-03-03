package com.degea9.android.foodrecipe.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.degea9.android.foodrecipe.local.dao.RecipeDao
import com.degea9.android.foodrecipe.local.entity.RecipeEntity

@Database(entities = [RecipeEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao

    companion object {

        const val DATABASE_NAME = "food-recipe-db"

    }
}