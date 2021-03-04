package com.degea9.android.foodrecipe.repository.di

import com.degea9.android.foodrecipe.domain.repository.RecipeRepository
import com.degea9.android.foodrecipe.domain.repository.SuggestionKeywordRepository
import com.degea9.android.foodrecipe.local.datasource.RecipeLocalDataSource
import com.degea9.android.foodrecipe.local.datasource.SuggestionKeywordLocalDataSource
import com.degea9.android.foodrecipe.remote.datasource.RecipeRemoteDataSource
import com.degea9.android.foodrecipe.remote.datasource.SuggestionKeywordRemoteDataSource
import com.degea9.android.foodrecipe.repository.RecipeRepositoryImpl
import com.degea9.android.foodrecipe.repository.SuggestionKeywordRepositoryImpl
import com.degea9.android.foodrecipe.repository.mapper.RecipeDataListMapper
import com.degea9.android.foodrecipe.repository.mapper.RecipeDataMapper
import com.degea9.android.foodrecipe.repository.mapper.SuggestionKeywordDataListMapper
import com.degea9.android.foodrecipe.repository.mapper.local.LocalSuggestionKeywordDataMapper
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
    fun provideRecipeRepository(service: FoodRecipeService,recipeRemoteDataSource: RecipeRemoteDataSource,recipeLocalDataSource: RecipeLocalDataSource,  recipeListMapper: RecipeDataListMapper,recipeDataMapper: RecipeDataMapper): RecipeRepository{
        return RecipeRepositoryImpl(service,recipeRemoteDataSource,recipeLocalDataSource,recipeListMapper, recipeDataMapper
        )
    }

    @Singleton
    @Provides
    fun provideSuggestionKeywordRepository(suggestionKeywordLocalDataSource: SuggestionKeywordLocalDataSource,
                                           suggestionKeywordRemoteDataSource: SuggestionKeywordRemoteDataSource,
                                           suggestionKeyWordListMapper: SuggestionKeywordDataListMapper,
                                           localSuggestionKeywordDataMapper: LocalSuggestionKeywordDataMapper) : SuggestionKeywordRepository{
        return SuggestionKeywordRepositoryImpl(suggestionKeywordLocalDataSource, suggestionKeywordRemoteDataSource, suggestionKeyWordListMapper, localSuggestionKeywordDataMapper)
    }
}