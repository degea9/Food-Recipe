package com.degea9.android.foodrecipe.repository

import androidx.paging.PagingSource
import com.degea9.android.foodrecipe.domain.model.Recipe
import com.degea9.android.foodrecipe.remote.BuildConfig
import com.degea9.android.foodrecipe.repository.mapper.RecipeDataListMapper
import com.degea9.foodrecipe.remote.FoodRecipeService
import com.degea9.foodrecipe.remote.response.RecipeResponse
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

class RecipePagingSource(
    private val service: FoodRecipeService,
    private val query: String,
    private val sort: String,
    private val mapper: RecipeDataListMapper
) : PagingSource<Int, Recipe>() {

    companion object {
        const val RECIPE_STARTING_OFFSET = 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Recipe> {
        Timber.d("query $query key ${params.key} loadSize ${params.loadSize}")
        val offset = params.key ?: RECIPE_STARTING_OFFSET
        Timber.d("offset $offset")
        return try {
            val recipeResponse = service.searchRecipes(
                apiKey = BuildConfig.SPOONACULAR_API_KEY,
                query = query,
                sort = sort,
                offset = offset,
                number = params.loadSize
            )
            val listRecipe = mapper.map(recipeResponse.results).orEmpty()
            LoadResult.Page(
                data = listRecipe,
                prevKey = if (offset == RECIPE_STARTING_OFFSET) null else offset - params.loadSize,
                nextKey = if (listRecipe.isEmpty()) null else offset + params.loadSize
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }


}
