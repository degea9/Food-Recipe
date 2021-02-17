package com.degea9.android.food.foodrecipe.home.di

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.degea9.android.food.foodrecipe.home.HomeActivity
import com.degea9.android.foodrecipe.core.di.CoreModuleDependencies
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [CoreModuleDependencies::class],
        modules = [HomeModule::class])
interface HomeComponent {

    fun inject(activity: HomeActivity)

    @Component.Factory
    interface Factory {
        fun create(
            dependentModule: CoreModuleDependencies,
            @BindsInstance activity: AppCompatActivity
        ): HomeComponent
    }
}