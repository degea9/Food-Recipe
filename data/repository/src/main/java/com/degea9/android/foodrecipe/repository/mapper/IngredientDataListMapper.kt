package com.degea9.android.foodrecipe.repository.mapper

import com.degea9.android.foodrecipe.domain.model.Ingredient
import com.degea9.foodrecipe.remote.response.IngredientResponse

class IngredientDataListMapper:ListMapper<IngredientResponse,Ingredient> {
    override fun map(input: List<IngredientResponse>?): List<Ingredient>? {
        return input?.map {
            Ingredient(
                id = it.id,
                name = it.name,
                localizedName = it.localizedName,
                image = it.image
            )
        }
    }
}