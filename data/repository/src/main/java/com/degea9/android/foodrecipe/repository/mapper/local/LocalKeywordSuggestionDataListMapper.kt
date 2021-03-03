package com.degea9.android.foodrecipe.repository.mapper.local

import com.degea9.android.foodrecipe.domain.model.SuggestionKeyword
import com.degea9.android.foodrecipe.local.entity.SuggestionKeywordEntity
import com.degea9.android.foodrecipe.repository.mapper.ListMapper
import javax.inject.Inject

class LocalSuggestionKeywordDataListMapper @Inject constructor():
    ListMapper<SuggestionKeywordEntity, SuggestionKeyword> {
    override fun map(input: List<SuggestionKeywordEntity>?): List<SuggestionKeyword>? {
        return input?.map {
            SuggestionKeyword(
                id = it.id,
                title = it.keyword,
                imageType = null
            )
        }
    }
}