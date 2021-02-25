package com.degea9.android.foodrecipe.core.di

import com.degea9.android.foodrecipe.domain.GetPopularRecipeUsecase
import com.degea9.android.foodrecipe.domain.GetRecipeDetailUseCase
import com.degea9.android.foodrecipe.domain.SearchRecipeUsecase
import com.degea9.android.foodrecipe.domain.dispatcher.UseCaseDispatchers
import com.degea9.android.foodrecipe.domain.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
class DomainModule {

    @ViewModelScoped
    @Provides
    fun provideGetPopularRecipeUsecase(recipeRepository: RecipeRepository,useCaseDispatchers: UseCaseDispatchers):GetPopularRecipeUsecase{
        return GetPopularRecipeUsecase(recipeRepository,useCaseDispatchers)
    }

    @ViewModelScoped
    @Provides
    fun provideSearchRecipeUsecase(recipeRepository: RecipeRepository,useCaseDispatchers: UseCaseDispatchers):SearchRecipeUsecase{
        return SearchRecipeUsecase(recipeRepository,useCaseDispatchers)
    }

    @ViewModelScoped
    @Provides
    fun provideGetRecipeDetailUsecase(recipeRepository: RecipeRepository,useCaseDispatchers: UseCaseDispatchers):GetRecipeDetailUseCase{
        return GetRecipeDetailUseCase(recipeRepository,useCaseDispatchers)
    }
}