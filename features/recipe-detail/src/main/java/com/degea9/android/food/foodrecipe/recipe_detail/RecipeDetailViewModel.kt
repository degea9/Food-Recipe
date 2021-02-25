package com.degea9.android.food.foodrecipe.recipe_detail

import androidx.lifecycle.*
import com.degea9.android.foodrecipe.core.BaseViewModel
import com.degea9.android.foodrecipe.domain.GetRecipeDetailUseCase
import com.degea9.android.foodrecipe.domain.model.Recipe
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class RecipeDetailViewModel @AssistedInject constructor(
    private val recipeDetailUseCase: GetRecipeDetailUseCase,
    @Assisted private val recipeID: Int
) : BaseViewModel() {

    var recipeDetailLiveData : LiveData<Recipe>? = null

    init {
        recipeDetailLiveData = recipeDetailUseCase.getRecipesDetail(recipeID).asLiveData(viewModelScope.coroutineContext+ Dispatchers.IO)
    }

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(id: Int): RecipeDetailViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory,
            id: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(id) as T
            }
        }
    }

}