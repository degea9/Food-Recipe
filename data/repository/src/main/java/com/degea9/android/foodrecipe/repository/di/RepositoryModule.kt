package com.degea9.android.foodrecipe.repository.di

import com.degea9.android.foodrecipe.domain.repository.RecipeRepository
import com.degea9.android.foodrecipe.remote.di.RemoteModule
import com.degea9.android.foodrecipe.repository.RecipeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module(
    includes = [
        RemoteModule::class,
    ]
)
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindRepository(repository: RecipeRepositoryImpl): RecipeRepository
}