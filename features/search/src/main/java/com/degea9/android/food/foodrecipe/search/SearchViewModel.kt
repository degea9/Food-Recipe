package com.degea9.android.food.foodrecipe.search

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.degea9.android.foodrecipe.core.BaseViewModel
import com.degea9.android.foodrecipe.domain.GetSuggestionKeywordUseCase
import com.degea9.android.foodrecipe.domain.SearchRecipeUsecase
import com.degea9.android.foodrecipe.domain.model.Recipe
import com.degea9.android.foodrecipe.domain.model.SuggestionKeyword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRecipeUseCase: SearchRecipeUsecase,
    private val getSuggestionUseCase: GetSuggestionKeywordUseCase,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private var currentQueryValue: String? = null

    private var currentSearchResult: Flow<PagingData<Recipe>>? = null

    private var _suggestionLiveData = MutableLiveData<List<SuggestionKeyword>>()
    var suggestionLiveData: LiveData<List<SuggestionKeyword>> = _suggestionLiveData

    /**
     * TO-DO :not hardcode using flow for search filter such as sort,
     */
    fun searchRecipe(query: String):Flow<PagingData<Recipe>>? {
        val lastResult = currentSearchResult
        if (query == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = query
        val newResult: Flow<PagingData<Recipe>> = searchRecipeUseCase.searchRecipe(query = query,sort="main course").cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }

    fun getSuggestKeyword(query: String, number: Int){
        viewModelScope.launch {
            _suggestionLiveData.postValue(getSuggestionUseCase.getSuggestionKeyword(query, number))
        }
    }

}