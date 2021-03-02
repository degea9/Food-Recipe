package com.degea9.android.foodrecipe.repository.di

import com.degea9.android.foodrecipe.domain.repository.RecipeRepository
import com.degea9.android.foodrecipe.remote.datasource.RecipeRemoteDataSource
import com.degea9.android.foodrecipe.repository.RecipeRepositoryImpl
import com.degea9.android.foodrecipe.repository.mapper.RecipeDataListMapper
import com.degea9.android.foodrecipe.repository.mapper.RecipeDataMapper
import com.degea9.android.foodrecipe.repository.mapper.SuggestionKeywordDataListMapper
import com.degea9.foodrecipe.remote.FoodRecipeService
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
    fun provideRecipeRepository(service: FoodRecipeService,recipeRemoteDataSource: RecipeRemoteDataSource,  recipeListMapper: RecipeDataListMapper,recipeDataMapper: RecipeDataMapper, suggestionKeywordDataListMapper: SuggestionKeywordDataListMapper): RecipeRepository{
        return RecipeRepositoryImpl(service,recipeRemoteDataSource,recipeListMapper, recipeDataMapper, suggestionKeywordDataListMapper
        )
    }
}