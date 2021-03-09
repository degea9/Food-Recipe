package com.degea9.android.foodrecipe.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.degea9.android.foodrecipe.local.dao.RecipeDao
import com.degea9.android.foodrecipe.local.dao.SuggestionKeywordDao
import com.degea9.android.foodrecipe.local.entity.RecipeEntity
import com.degea9.android.foodrecipe.local.entity.SuggestionKeywordEntity

@Database(entities = [RecipeEntity::class, SuggestionKeywordEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun suggestionKeywordDao(): SuggestionKeywordDao

    companion object {

        const val DATABASE_NAME = "food-recipe-db"

        val MIGRATTION_1_2 = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS `suggestionKeyword` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `search_time` INTEGER NOT NULL, `keyword` TEXT NOT NULL)")
                database.execSQL("ALTER TABLE recipes ADD COLUMN isFavourite INTEGER")
                database.execSQL("ALTER TABLE recipes ADD COLUMN isHistory INTEGER")
            }

        }
    }
}