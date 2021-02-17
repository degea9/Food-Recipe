package com.degea9.android.food.foodrecipe.home.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.degea9.android.food.foodrecipe.home.HomeActivity
import com.degea9.android.food.foodrecipe.home.HomeViewModel
import com.degea9.android.food.foodrecipe.home.HomeViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
class HomeModule {

    @Provides
    fun provideHomeViewModel(
        activity: AppCompatActivity,
        factory: HomeViewModelFactory
    ) =
        ViewModelProvider(activity, factory).get(HomeViewModel::class.java)
}