package com.degea9.android.foodrecipe.repository.di

import com.degea9.android.foodrecipe.domain.repository.RecipeRepository
import com.degea9.android.foodrecipe.remote.datasource.RecipeRemoteDataSource
import com.degea9.android.foodrecipe.repository.RecipeRepositoryImpl
import com.degea9.android.foodrecipe.repository.mapper.RecipeDataListMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRecipeRepository(recipeRemoteDataSource: RecipeRemoteDataSource,  mapper: RecipeDataListMapper): RecipeRepository{
        return RecipeRepositoryImpl(recipeRemoteDataSource,mapper)
    }
}