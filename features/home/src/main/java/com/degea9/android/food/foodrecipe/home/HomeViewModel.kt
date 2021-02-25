package com.degea9.android.food.foodrecipe.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.degea9.android.food.foodrecipe.model.CategoryRecipes
import com.degea9.android.foodrecipe.core.BaseViewModel
import com.degea9.android.foodrecipe.domain.GetPopularRecipeUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularRecipeUsecase: GetPopularRecipeUsecase,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    var categoryRecipesList: Flow<CategoryRecipes>? = null

    init {
        Timber.d("init called")
        val listCategory =
            listOf<String>("popularity", "healthiness", "price", "random", "cholesterol", "carbs")
        categoryRecipesList = listCategory.asFlow().flatMapMerge { categoryName ->
            getPopularRecipeUsecase.getRecipes(categoryName)
                .map { CategoryRecipes(categoryName, recipes = it) }
        }.shareIn(viewModelScope, replay = listCategory.size, started = SharingStarted.Lazily)

    }

    //val popularRecipes : LiveData<List<Recipe>> =  getPopularRecipeUsecase.getRecipes("popularity").asLiveData(viewModelScope.coroutineContext+Dispatchers.IO)

    //val heathRecipes : LiveData<List<Recipe>> =  getPopularRecipeUsecase.getRecipes("healthiness").asLiveData(viewModelScope.coroutineContext+Dispatchers.IO)
}