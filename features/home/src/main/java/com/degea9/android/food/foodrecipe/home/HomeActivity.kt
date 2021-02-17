package com.degea9.android.food.foodrecipe.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.degea9.android.food.foodrecipe.home.di.DaggerHomeComponent
import com.degea9.android.foodrecipe.core.BaseActivity
import com.degea9.android.foodrecipe.core.di.CoreModuleDependencies
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var homeViewModel: HomeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        initCoreDependentInjection()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        homeViewModel.getRecipe()
    }

    private fun initCoreDependentInjection() {
        val coreModuleDependencies = EntryPointAccessors.fromApplication(
            applicationContext,
            CoreModuleDependencies::class.java
        )

        DaggerHomeComponent.factory().create(
            dependentModule = coreModuleDependencies,
            activity = this
        )
            .inject(this)
    }
}