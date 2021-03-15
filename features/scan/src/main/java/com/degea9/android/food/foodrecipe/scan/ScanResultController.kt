package com.degea9.android.food.foodrecipe.scan

import com.airbnb.epoxy.TypedEpoxyController
import com.degea9.android.foodrecipe.domain.model.ImageAnalysis
import com.degea9.android.foodrecipe.domain.model.Recipe

class ScanResultController(private val onItemClick: (recipe: Recipe)-> Unit, private val onCategoryClick: (category: String?) -> Unit) : TypedEpoxyController<ImageAnalysis>() {
    override fun buildModels(data: ImageAnalysis?) {
        data?.let { data1->
            CategoryBindingModel_()
                    .id(-1)
                    .category(data1.category)
                    .spanSizeOverride { totalSpanCount, _, _ -> totalSpanCount }
                    .clickListener{ view ->
                        onCategoryClick(data1.category?.name)
                    }
                    .addTo(this)
            data1.recipes?.forEach {
                ResultRecipeBindingModel_()
                        .id(it.id)
                        .recipe(it)
                        .clickListener{ v->
                            onItemClick(it)
                        }
                        .addTo(this)
            }
        }
    }
}