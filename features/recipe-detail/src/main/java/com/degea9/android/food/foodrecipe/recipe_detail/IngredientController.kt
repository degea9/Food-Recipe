package com.degea9.android.food.foodrecipe.recipe_detail

import com.airbnb.epoxy.TypedEpoxyController
import com.degea9.android.foodrecipe.domain.model.Ingredient
import timber.log.Timber

class IngredientController : TypedEpoxyController<List<Ingredient>?>() {
    override fun buildModels(data:List<Ingredient>?) {
        Timber.d("buildModels data $data")
        data?.map {
            IngredientBindingModel_()
                .id(it.id)
                .ingredient(it)
                .addTo(this)
        }
    }
}