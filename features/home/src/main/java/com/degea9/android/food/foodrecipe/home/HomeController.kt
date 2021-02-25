package com.degea9.android.food.foodrecipe.home

import android.view.View
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.TypedEpoxyController
import com.airbnb.epoxy.carousel
import com.degea9.android.food.foodrecipe.model.CategoryRecipes
import com.degea9.android.foodrecipe.domain.model.Recipe
import timber.log.Timber

class HomeController(private val onCategoryClick: (String)->Unit,private val onItemClick: (Recipe)->Unit) : TypedEpoxyController<List<CategoryRecipes>>() {
    override fun buildModels(categoryRecipes: List<CategoryRecipes>?) {
        categoryRecipes?.forEach {categoryRecipes->
            title {
                id(categoryRecipes.category)
                title(categoryRecipes.category)
                clickListener(View.OnClickListener {
                    onCategoryClick(categoryRecipes.category)
                })
            }
            // Carousel Item
            val carouselItemModels = categoryRecipes.recipes.map { currentItem ->
                RecipeBindingModel_()
                    .id(currentItem.id)
                    .recipe(currentItem)
                    .clickListener { v ->
                        Timber.d("Click to ${currentItem.title}")
                        onItemClick(currentItem)
                    }

            }

            // Carousel
            // This extension function come with epoxy
            carousel {
                id(categoryRecipes.category)
                padding(Carousel.Padding(30, 30))
                Carousel.setDefaultGlobalSnapHelperFactory(null)
                models(carouselItemModels)
            }
        }
    }
}