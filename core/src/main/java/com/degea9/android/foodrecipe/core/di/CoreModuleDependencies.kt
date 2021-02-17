package com.degea9.android.foodrecipe.core.di

import com.degea9.android.foodrecipe.domain.GetPopularRecipeUsecase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface CoreModuleDependencies {

    /*
     Provision methods to provide dependencies to components that depend on this component
   */
    fun getPopularRecipeUseCase():GetPopularRecipeUsecase
}