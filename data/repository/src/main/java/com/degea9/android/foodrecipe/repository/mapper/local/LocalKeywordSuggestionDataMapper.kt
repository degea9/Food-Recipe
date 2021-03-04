package com.degea9.android.foodrecipe.repository.mapper.local

import com.degea9.android.foodrecipe.domain.model.SuggestionKeyword
import com.degea9.android.foodrecipe.local.entity.SuggestionKeywordEntity
import com.degea9.android.foodrecipe.repository.mapper.ListMapper
import com.degea9.android.foodrecipe.repository.mapper.Mapper
import javax.inject.Inject

class LocalSuggestionKeywordDataMapper @Inject constructor():
    Mapper<SuggestionKeywordEntity, SuggestionKeyword> {
    override fun map(input: SuggestionKeywordEntity): SuggestionKeyword {
        return SuggestionKeyword(
                id = input.id,
                title = input.keyword,
                imageType = null
        )
    }

}