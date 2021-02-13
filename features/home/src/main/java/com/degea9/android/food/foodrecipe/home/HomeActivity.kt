package com.degea9.android.food.foodrecipe.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.degea9.android.foodrecipe.base.BaseActivity
import com.degea9.android.foodrecipe.remote.BuildConfig
import com.degea9.foodrecipe.remote.FoodRecipeApi

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.getRecipe()
    }
}