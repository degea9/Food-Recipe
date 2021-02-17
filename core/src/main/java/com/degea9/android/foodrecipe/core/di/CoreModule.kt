package com.degea9.android.foodrecipe.core.di

import com.degea9.android.foodrecipe.domain.dispatcher.UseCaseDispatchers
import com.degea9.android.foodrecipe.remote.di.RemoteModule
import com.degea9.android.foodrecipe.repository.di.RepositoryModule
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@InstallIn(SingletonComponent::class)
@Module(
    includes = [
        RepositoryModule::class]
)
class CoreModule {
    @Provides
    fun provideUseCaseDispatchers(): UseCaseDispatchers {
        return UseCaseDispatchers(Dispatchers.IO, Dispatchers.Default, Dispatchers.Main)
    }
}