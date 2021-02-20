package com.degea9.android.food.foodrecipe.category_recipes.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.degea9.android.food.foodrecipe.category_recipes.R
import com.degea9.android.foodrecipe.domain.model.Recipe
import timber.log.Timber

/**
 * View Holder for a [Repo] RecyclerView list item.
 */
class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val name: TextView = view.findViewById(R.id.tv_recipe_name)
    private val ivRecipe: ImageView = view.findViewById(R.id.iv_recipe)

    private var recipe: Recipe? = null

    init {
        view.setOnClickListener {
            Timber.d("recipe click title ${recipe?.title}")
        }
    }

    fun bind(repo: Recipe?) {
        if (repo == null) {
            val resources = itemView.resources
            name.text = "Loading"

        } else {
            showRepoData(repo)
        }
    }

    private fun showRepoData(recipe: Recipe) {
        this.recipe = recipe
        name.text = recipe.title
        val requestOptions = RequestOptions()
        Glide.with(ivRecipe.context).setDefaultRequestOptions(requestOptions.centerCrop()).load(recipe.image)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(ivRecipe)

    }

    companion object {
        fun create(parent: ViewGroup): RecipeViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recipe_view_item, parent, false)
            return RecipeViewHolder(view)
        }
    }
}
