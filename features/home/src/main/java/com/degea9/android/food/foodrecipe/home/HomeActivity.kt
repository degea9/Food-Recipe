package com.degea9.android.food.foodrecipe.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
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