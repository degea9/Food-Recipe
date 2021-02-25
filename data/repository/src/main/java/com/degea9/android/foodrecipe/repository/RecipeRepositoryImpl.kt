package com.degea9.android.foodrecipe.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.degea9.android.foodrecipe.domain.model.Recipe
import com.degea9.android.foodrecipe.domain.repository.RecipeRepository
import com.degea9.android.foodrecipe.remote.datasource.RecipeRemoteDataSource
import com.degea9.android.foodrecipe.repository.mapper.RecipeDataListMapper
import com.degea9.foodrecipe.remote.FoodRecipeService
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val service: FoodRecipeService,
    private val recipeRemoteDataSource: RecipeRemoteDataSource,
    private val mapper: RecipeDataListMapper
) : RecipeRepository {
    override suspend fun getCategoryRecipes(category: String): List<Recipe> {
        return mapper.map(recipeRemoteDataSource.getCategoryRecipes(category).results).orEmpty()
    }

    override fun searchRecipe(query:String,sort:String): Flow<PagingData<Recipe>> {
        Timber.d("query: $query sort $sort")
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false,initialLoadSize = NETWORK_PAGE_SIZE*2),
            pagingSourceFactory = { RecipePagingSource(service = service,query = query,sort = sort,mapper = mapper) }
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 10
    }
}