package com.degea9.android.food.foodrecipe.search

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.degea9.android.foodrecipe.core.BaseViewModel
import com.degea9.android.foodrecipe.domain.SearchRecipeUsecase
import com.degea9.android.foodrecipe.domain.model.Recipe
import com.degea9.android.foodrecipe.domain.model.SuggestionKeyword
import com.degea9.android.foodrecipe.domain.search.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRecipeUseCase: SearchRecipeUsecase,
    private val getSuggestionUseCase: GetSuggestionKeywordUseCase,
    private val getLocalKeywordUseCase: GetLocalKeywordUseCase,
    private val getHistoryRecipesUseCase: GetHistoryRecipesUseCase,
    private val saveLocalSuggestionKeywordUseCase: SaveLocalSuggestionKeywordUseCase,
    private val addHistoryRecipesUseCase: AddHistoryRecipesUseCase
) : BaseViewModel() {

    private var currentQueryValue: String? = null

    private var currentSearchResult: Flow<PagingData<Recipe>>? = null

    private var _suggestionLiveData = MutableLiveData<List<SuggestionKeyword>>()
    var suggestionLiveData: LiveData<List<SuggestionKeyword>> = _suggestionLiveData

    private var _searchHistoryLiveData = MutableLiveData<SearchHistoryUI>()
    var searchHistoryLiveData: LiveData<SearchHistoryUI> = _searchHistoryLiveData

    var searchSuggestionJob: Job? = null
    var searchHistoryJob: Job? =null

    /**
     * TO-DO :not hardcode using flow for search filter such as sort,
     */
    fun searchRecipe(query: String):Flow<PagingData<Recipe>>? {
        searchHistoryJob?.cancel()
        searchSuggestionJob?.cancel()
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
        searchHistoryJob?.cancel()
        searchSuggestionJob = viewModelScope.launch {
            _suggestionLiveData.postValue(getSuggestionUseCase.getRemoteSuggestionKeyword(query, number))
        }
    }

    fun getSearchHistory(){
        searchSuggestionJob?.cancel()
        searchHistoryJob = viewModelScope.launch {
            val keyword = async(Dispatchers.IO){
                getLocalKeywordUseCase.getLocalSuggestionKeyword()
            }
            val recipesHistory = async(Dispatchers.IO) {
                getHistoryRecipesUseCase.getHistoryRecipes()
            }
            val keywordResult = keyword.await()
            val recipesHistoryResult = recipesHistory.await()

            Timber.d("getSearchHistory: ${recipesHistoryResult.first()}")
            _searchHistoryLiveData.postValue(SearchHistoryUI(recipesHistoryResult.first(), keywordResult.first()))

        }
    }
    fun saveSuggestionKeyword(suggestionKeyword: SuggestionKeyword){
        GlobalScope.launch(Dispatchers.IO) {
            Timber.d("saveKeyword: call")
            saveLocalSuggestionKeywordUseCase.saveKeyword(suggestionKeyword)
        }
    }
    fun addHistoryRecipe(recipe: Recipe){
        GlobalScope.launch(Dispatchers.IO){
            addHistoryRecipesUseCase.addHistoryRecipes(recipe)
        }
    }



}