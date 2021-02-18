package com.degea9.android.foodrecipe.core.di

import com.degea9.android.foodrecipe.domain.GetPopularRecipeUsecase
import com.degea9.android.foodrecipe.domain.dispatcher.UseCaseDispatchers
import com.degea9.android.foodrecipe.domain.repository.RecipeRepository
import com.degea9.android.foodrecipe.repository.di.RepositoryModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DomainModule {

    @Singleton
    @Provides
    fun provideGetPopularRecipeUsecase(recipeRepository: RecipeRepository,useCaseDispatchers: UseCaseDispatchers):GetPopularRecipeUsecase{
        return GetPopularRecipeUsecase(recipeRepository,useCaseDispatchers)
    }
}