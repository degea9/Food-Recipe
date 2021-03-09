package com.degea9.android.foodrecipe.repository.mapper

import com.degea9.android.foodrecipe.domain.model.Recipe
import com.degea9.android.foodrecipe.local.entity.RecipeEntity
import javax.inject.Inject

class DataMappersFacade @Inject constructor() {
    fun mapLocalRecipeToDomain(input: RecipeEntity?): Recipe {
        return Recipe(
            id = input?.id ?: 0,
            title = input?.title,
            summary = "",
            image = input?.image,
            imageType = "",
            sourceName = "",
            dishTypes = emptyList(),
            analyzedInstructions = null,
            extendedIngredients = null
        )
    }

    fun mapDomainRecipeToLocal(input:Recipe):RecipeEntity {
        return RecipeEntity(
            id = input.id,
            title = input.title,
            image = input.image,
            isFavourite = false,
            isHistory = false
        )
    }
}