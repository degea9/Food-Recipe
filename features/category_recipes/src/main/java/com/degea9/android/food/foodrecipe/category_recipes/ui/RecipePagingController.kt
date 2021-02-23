package com.degea9.android.food.foodrecipe.category_recipes.ui

import com.airbnb.epoxy.EpoxyModel
import com.degea9.android.food.foodrecipe.category_recipes.CategoryRecipeBindingModel_
import com.degea9.android.food.foodrecipe.category_recipes.epoxy_paging3.PagingDataEpoxyController
import com.degea9.android.foodrecipe.domain.model.Recipe
import timber.log.Timber

class RecipePagingController: PagingDataEpoxyController<Recipe>() {
    override fun buildItemModel(currentPosition: Int, item: Recipe?): EpoxyModel<*> {
        return CategoryRecipeBindingModel_()
            .id(item?.id)
            .recipe(item)
            .clickListener { v ->
                Timber.d("Click to ${item?.title}")
            }
    }
}