package com.degea9.android.foodrecipe.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.degea9.android.foodrecipe.local.dao.RecipeDao
import com.degea9.android.foodrecipe.local.dao.SuggestionKeywordDao
import com.degea9.android.foodrecipe.local.entity.RecipeEntity
import com.degea9.android.foodrecipe.local.entity.SuggestionKeywordEntity

@Database(entities = [RecipeEntity::class, SuggestionKeywordEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun suggestionKeywordDao(): SuggestionKeywordDao

    companion object {

        const val DATABASE_NAME = "food-recipe-db"

    }
}