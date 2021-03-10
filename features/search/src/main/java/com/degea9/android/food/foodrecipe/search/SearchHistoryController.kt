package com.degea9.android.food.foodrecipe.search

import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.TypedEpoxyController
import com.airbnb.epoxy.carousel
import com.degea9.android.foodrecipe.domain.model.Recipe
import timber.log.Timber

class SearchHistoryController(private val onRecipeClick: (recipe: Recipe) -> Unit, private val onSuggestionClick: (query: String?) -> Unit): TypedEpoxyController<SearchHistoryUI>() {
    override fun buildModels(data: SearchHistoryUI?) {
        data?.recipes?.let {
            val carouselItemModels = it.map { currentItem ->
                SearchRecipeBindingModel_()
                        .id(currentItem.id)
                        .recipe(currentItem)
                        .clickListener { v ->
                            Timber.d("Click to ${currentItem.title}")
                            onRecipeClick(currentItem)
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
        data?.suggestionKeywords?.forEach {
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