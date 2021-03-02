package com.degea9.android.food.foodrecipe.search

import com.airbnb.epoxy.TypedEpoxyController
import com.degea9.android.foodrecipe.domain.model.SuggestionKeyword

class SuggestionController: TypedEpoxyController<List<SuggestionKeyword>>() {
    override fun buildModels(data: List<SuggestionKeyword>?) {
        data?.forEach {
            SuggestionBindingModel_()
                .id(it.id)
                .suggestion(it)
                .addTo(this)
        }
    }
}