package com.degea9.android.food.foodrecipe.search

import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.Typed2EpoxyController
import com.airbnb.epoxy.carousel
import com.degea9.android.foodrecipe.domain.model.Recipe
import com.degea9.android.foodrecipe.domain.model.SuggestionKeyword
import timber.log.Timber

class SearchHistoryController(private val onItemClick: (recipe: Recipe) -> Unit, private val onSuggestionClick: (query: String?) -> Unit): Typed2EpoxyController<List<Recipe>, List<SuggestionKeyword>>() {
    override fun buildModels(recipes: List<Recipe>?, suggestions: List<SuggestionKeyword>?) {
        recipes?.let {
            val carouselItemModels = it.map { currentItem ->
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
                id(-2)
                padding(Carousel.Padding(30, 30))
                Carousel.setDefaultGlobalSnapHelperFactory(null)
                models(carouselItemModels)
            }
        }
        suggestions?.forEach {
            SuggestionBindingModel_()
                    .id(it.id)
                    .clickListener { _ ->
                        onSuggestionClick(it.title)
                    }
                    .suggestion(it)
                    .addTo(this)
        }

    }
}