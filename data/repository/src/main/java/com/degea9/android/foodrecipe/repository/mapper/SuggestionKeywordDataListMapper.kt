package com.degea9.android.foodrecipe.repository.mapper

import com.degea9.android.foodrecipe.domain.model.SuggestionKeyword
import com.degea9.android.foodrecipe.remote.response.SuggestionKeywordResponse
import javax.inject.Inject

class SuggestionKeywordDataListMapper @Inject constructor(): ListMapper<SuggestionKeywordResponse, SuggestionKeyword> {
    override fun map(input: List<SuggestionKeywordResponse>?): List<SuggestionKeyword>? {
        return input?.map {
            SuggestionKeyword(
                id = it.id,
                title = it.title,
                imageType = it.imageType
            )
        }
    }
}