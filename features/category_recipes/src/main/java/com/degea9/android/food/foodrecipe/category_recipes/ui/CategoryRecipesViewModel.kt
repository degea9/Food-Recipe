package com.degea9.android.food.foodrecipe.category_recipes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.degea9.android.foodrecipe.core.BaseViewModel
import com.degea9.android.foodrecipe.domain.SearchRecipeUsecase
import com.degea9.android.foodrecipe.domain.model.Recipe
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.Flow

class CategoryRecipesViewModel @AssistedInject constructor(
    private val searchRecipeUsecase: SearchRecipeUsecase,
    @Assisted private val category: String
) : BaseViewModel() {

    var recipePagingDataFlow : Flow<PagingData<Recipe>> ? = null

    init {
        recipePagingDataFlow = searchRecipeUsecase.searchRecipe(query = "",sort=category).cachedIn(viewModelScope)
    }

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(category: String): CategoryRecipesViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory,
            category: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(category) as T
            }
        }
    }
}