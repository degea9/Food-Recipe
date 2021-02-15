package com.degea9.android.foodrecipe.repository.mapper

import com.degea9.android.foodrecipe.domain.model.Recipe
import com.degea9.foodrecipe.remote.response.RecipeResponse

class RecipeDataListMapper : ListMapper<RecipeResponse, Recipe> {
    override fun map(input: List<RecipeResponse>?): List<Recipe>? {
        return input?.map { recipeDataModel ->
            Recipe(
                id = recipeDataModel.id,
                title = recipeDataModel.title,
                summary = recipeDataModel.summary,
                image = recipeDataModel.image,
                imageType = recipeDataModel.imageType,
                sourceName = recipeDataModel.sourceName,
                dishTypes = recipeDataModel.dishTypes,
                analyzedInstructions = MapperFactory.createListMapper<InstructionDataListMapper>().map(recipeDataModel.analyzedInstructions)
            )
        }
    }
}