package com.degea9.android.foodrecipe.repository.mapper.local

import com.degea9.android.foodrecipe.domain.model.Recipe
import com.degea9.android.foodrecipe.local.entity.RecipeEntity
import com.degea9.android.foodrecipe.repository.mapper.Mapper
import javax.inject.Inject

class RecipeLocalMapper @Inject constructor() : Mapper<RecipeEntity, Recipe> {
    override fun map(input: RecipeEntity): Recipe {
        return Recipe(
            id = input.id,
            title = input.title,
            summary = "",
            image = input.image,
            imageType = "",
            sourceName = "",
            dishTypes = emptyList(),
            analyzedInstructions = null,
            extendedIngredients = null
        )
    }
}