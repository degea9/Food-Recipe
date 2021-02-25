package com.degea9.android.food.foodrecipe.home

import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.TypedEpoxyController
import com.airbnb.epoxy.carousel
import com.degea9.android.food.foodrecipe.model.CategoryRecipes
import timber.log.Timber

class HomeController(private val onCategoryClick: (String)->Unit) : TypedEpoxyController<List<CategoryRecipes>>() {
    override fun buildModels(categoryRecipes: List<CategoryRecipes>?) {
        categoryRecipes?.forEach {
            title {
                id(it.category)
                title(it.category)
            }
            // Carousel Item
            val carouselItemModels = it.recipes.map { currentItem ->
                PopularRecipeBindingModel_()
                    .id(currentItem.id)
                    .recipePopularItem(currentItem)
                    .clickListener { v ->
                        Timber.d("Click to ${currentItem.title}")
                        onCategoryClick(it.category)
                    }

            }

            // Carousel
            // This extension function come with epoxy
            carousel {
                id(it.category)
                padding(Carousel.Padding(30, 30))
                Carousel.setDefaultGlobalSnapHelperFactory(null)
                models(carouselItemModels)
            }
        }
    }
}