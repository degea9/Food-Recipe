package com.degea9.android.food.foodrecipe.home

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.degea9.android.foodrecipe.core.BaseActivity

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.getRecipe()
    }
}