package com.degea9.android.food.foodrecipe.search

import com.airbnb.epoxy.EpoxyModel
import com.degea9.android.foodrecipe.domain.model.Recipe
import com.degea9.android.foodrecipe.epoxy_paging3.PagingDataEpoxyController
import timber.log.Timber

class SearchResultPagingController(private val onItemClick: (Recipe?)->Unit): PagingDataEpoxyController<Recipe>() {
    override fun buildItemModel(currentPosition: Int, item: Recipe?): EpoxyModel<*> {
        return SearchResultBindingModel_()
            .id(item?.id)
            .recipe(item)
            .clickListener { v ->
                Timber.d("Click to ${item?.title}")
                onItemClick(item)
            }
    }
}