package com.degea9.android.food.foodrecipe.search

import android.view.View
import androidx.databinding.adapters.SearchViewBindingAdapter
import com.airbnb.epoxy.TypedEpoxyController
import com.degea9.android.foodrecipe.domain.model.SuggestionKeyword

class SuggestionController(private val onSuggestionClick: (query: String?)->Unit, private val onSeeAllClick: () -> Unit): TypedEpoxyController<List<SuggestionKeyword>>() {
    override fun buildModels(data: List<SuggestionKeyword>?) {
        data?.forEach {
            SuggestionBindingModel_()
                .id(it.id)
                .clickListener{ view->
                    onSuggestionClick(it.title)
                }
                .suggestion(it)
                .addTo(this)
        }
        data?.let {
            if (it.isNotEmpty()){
                SeeAllBindingModel_()
                        .id(-1)
                        .clickListener{ _ ->
                            onSeeAllClick.invoke()
                        }
                        .addTo(this)
            }
        }

    }
}