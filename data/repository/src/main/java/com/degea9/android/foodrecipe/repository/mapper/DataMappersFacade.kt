package com.degea9.android.foodrecipe.repository.mapper

import com.degea9.android.foodrecipe.domain.model.Recipe
import com.degea9.android.foodrecipe.domain.model.SuggestionKeyword
import com.degea9.android.foodrecipe.local.entity.RecipeEntity
import com.degea9.android.foodrecipe.local.entity.SuggestionKeywordEntity
import com.degea9.android.foodrecipe.remote.response.SuggestionKeywordResponse
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

    fun mapLocalRecipeToDomainOrNull(input: RecipeEntity?): Recipe? {
        return input?.let {
            Recipe(
                    id = it.id ?: 0,
                    title = it.title,
                    summary = "",
                    image = it.image,
                    imageType = "",
                    sourceName = "",
                    dishTypes = emptyList(),
                    analyzedInstructions = null,
                    extendedIngredients = null
            )
        }
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

    fun mapLocalRecipesToDomain(input: List<RecipeEntity>?): List<Recipe>{
        return input?.map {
            Recipe(
                    id = it.id,
                    title = it.title,
                    summary = null,
                    image = it.image,
                    imageType = null,
                    sourceName = null,
                    dishTypes = null,
                    analyzedInstructions = null,
                    extendedIngredients = null
            )
        }?: emptyList()
    }

    fun mapDomainSuggestionKeywordToLocal(input: SuggestionKeyword?): SuggestionKeywordEntity? {
        return input?.let {
            SuggestionKeywordEntity(id = 0, searchTime = System.currentTimeMillis(), keyword = input.title.orEmpty())
        }

    }

    fun mapLocalSuggestionKeywordToDomain(input: SuggestionKeywordEntity): SuggestionKeyword {
        return SuggestionKeyword(
                id = input.id,
                title = input.keyword,
                imageType = null
        )

    }

    fun mapLocalSuggestionKeywordListToDomain(input: List<SuggestionKeywordEntity>?): List<SuggestionKeyword>? {
        return input?.map {
            SuggestionKeyword(
                    id = it.id,
                    title = it.keyword,
                    imageType = null
            )
        }

    }

    fun mapRemoteSuggestionKeywordListToDomain(input: List<SuggestionKeywordResponse>?): List<SuggestionKeyword>? {
        return input?.map {
            SuggestionKeyword(
                    id = it.id,
                    title = it.title,
                    imageType = it.imageType
            )
        }

    }
}