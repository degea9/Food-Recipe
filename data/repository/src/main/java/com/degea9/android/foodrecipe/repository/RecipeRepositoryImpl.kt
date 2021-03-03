package com.degea9.android.foodrecipe.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.degea9.android.foodrecipe.domain.model.Recipe
import com.degea9.android.foodrecipe.domain.model.SuggestionKeyword
import com.degea9.android.foodrecipe.domain.repository.RecipeRepository
import com.degea9.android.foodrecipe.local.datasource.RecipeLocalDataSource
import com.degea9.android.foodrecipe.remote.datasource.RecipeRemoteDataSource
import com.degea9.android.foodrecipe.repository.mapper.RecipeDataListMapper
import com.degea9.android.foodrecipe.repository.mapper.RecipeDataMapper
import com.degea9.android.foodrecipe.repository.mapper.SuggestionKeywordDataListMapper
import com.degea9.foodrecipe.remote.FoodRecipeService
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val service: FoodRecipeService,
    private val recipeRemoteDataSource: RecipeRemoteDataSource,
    private val recipeLocalDataSource: RecipeLocalDataSource,
    private val listMapper: RecipeDataListMapper,
    private val recipeMapper:RecipeDataMapper,
    private val suggestionKeyWordListMapper: SuggestionKeywordDataListMapper
) : RecipeRepository {
    override suspend fun getCategoryRecipes(category: String): List<Recipe> {
        return listMapper.map(recipeRemoteDataSource.getCategoryRecipes(category).results).orEmpty()
    }

    override suspend fun getRecipeDetail(id: Int): Recipe {
        return recipeMapper.map(recipeRemoteDataSource.getRecipeDetail(id))
    }

    override fun searchRecipe(query:String,sort:String): Flow<PagingData<Recipe>> {
        Timber.d("query: $query sort $sort")
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false,initialLoadSize = NETWORK_PAGE_SIZE*2),
            pagingSourceFactory = { RecipePagingSource(service = service,query = query,sort = sort,mapper = listMapper) }
        ).flow
    }

    override suspend fun getSuggestionKeyword(query: String, number: Int): List<SuggestionKeyword> {
        return suggestionKeyWordListMapper.map(recipeRemoteDataSource.getSuggestionKeyword(query, number)).orEmpty()
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 10
    }
}