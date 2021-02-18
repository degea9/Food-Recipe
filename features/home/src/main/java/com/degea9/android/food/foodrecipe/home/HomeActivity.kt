package com.degea9.android.food.foodrecipe.home

import android.os.Bundle
import androidx.activity.viewModels
import com.degea9.android.foodrecipe.core.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    private val homeViewModel: HomeViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        initCoreDependentInjection()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        homeViewModel.getRecipe()
    }

    private fun initCoreDependentInjection() {
//        val coreModuleDependencies = EntryPointAccessors.fromApplication(
//            applicationContext,
//            CoreModuleDependencies::class.java
//        )
//
//        DaggerHomeComponent.factory().create(
//            dependentModule = coreModuleDependencies,
//            activity = this
//        )
//            .inject(this)
    }
}