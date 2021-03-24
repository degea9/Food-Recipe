package com.degea9.android.foodrecipe.repository.mapper

import com.degea9.android.foodrecipe.domain.model.Recipe
import com.degea9.foodrecipe.remote.response.RecipeResponse
import javax.inject.Inject

class RecipeDataMapper  @Inject constructor(): Mapper<RecipeResponse,Recipe> {
    override fun map(input: RecipeResponse): Recipe {
        return Recipe(
            id = input.id,
            title = input.title,
            summary = input.summary,
            image = input.image,
            imageType = input.imageType,
            sourceName = input.sourceName,
            dishTypes = input.dishTypes,
            analyzedInstructions = MapperFactory.createListMapper<InstructionDataListMapper>().map(input.analyzedInstructions),
            extendedIngredients = MapperFactory.createListMapper<IngredientDataListMapper>().map(input.extendedIngredients)
        )
    }
}