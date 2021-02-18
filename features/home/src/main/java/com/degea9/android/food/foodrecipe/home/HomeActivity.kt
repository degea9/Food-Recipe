package com.degea9.android.food.foodrecipe.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.degea9.android.foodrecipe.core.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupObserver()
    }

    private fun setupObserver(){
        homeViewModel.popularRecipes.observe(this, Observer {
            Timber.d("popularRecipes size ${it.size}")
        })
    }
}