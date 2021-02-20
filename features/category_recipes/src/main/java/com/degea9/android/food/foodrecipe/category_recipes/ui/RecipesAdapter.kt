package com.degea9.android.food.foodrecipe.category_recipes.ui

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.degea9.android.foodrecipe.domain.model.Recipe

/**
 * Adapter for the list of recipes.
 */
class RecipesAdapter : PagingDataAdapter<Recipe, RecipeViewHolder>(RECIPE_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipeItem = getItem(position)
        if (recipeItem != null) {
            holder.bind(recipeItem)
        }
    }

    companion object {
        private val RECIPE_COMPARATOR = object : DiffUtil.ItemCallback<Recipe>() {
            override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean =
                oldItem == newItem
        }
    }
}