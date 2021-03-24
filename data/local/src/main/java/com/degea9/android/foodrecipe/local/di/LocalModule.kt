package com.degea9.android.foodrecipe.local.di

import android.app.Application
import androidx.room.Room
import com.degea9.android.foodrecipe.local.dao.RecipeDao
import com.degea9.android.foodrecipe.local.dao.SuggestionKeywordDao
import com.degea9.android.foodrecipe.local.database.AppDatabase
import com.degea9.android.foodrecipe.local.database.AppDatabase.Companion.DATABASE_NAME
import com.degea9.android.foodrecipe.local.datasource.RecipeLocalDataSource
import com.degea9.android.foodrecipe.local.datasource.RecipeLocalDataSourceImpl
import com.degea9.android.foodrecipe.local.datasource.SuggestionKeywordLocalDataSource
import com.degea9.android.foodrecipe.local.datasource.SuggestionKeywordLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
                application,
                AppDatabase::class.java,
                DATABASE_NAME
        )
                .build()
    }

    @Singleton
    @Provides
    fun provideRecipeDao(appDatabase: AppDatabase): RecipeDao =
        appDatabase.recipeDao()

    @Singleton
    @Provides
    fun provideSuggestionKeywordDao(appDatabase: AppDatabase): SuggestionKeywordDao = appDatabase.suggestionKeywordDao()

    @Singleton
    @Provides
    fun provideRecipeLocalDataSource(recipeDao: RecipeDao): RecipeLocalDataSource{
        return RecipeLocalDataSourceImpl(recipeDao)
    }

    @Singleton
    @Provides
    fun provideSuggestionKeywordLocalDataSource(suggestionKeywordDao: SuggestionKeywordDao): SuggestionKeywordLocalDataSource{
        return  SuggestionKeywordLocalDataSourceImpl(suggestionKeywordDao)
    }
}