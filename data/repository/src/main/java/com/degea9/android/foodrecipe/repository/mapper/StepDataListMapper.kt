package com.degea9.android.foodrecipe.repository.mapper

import com.degea9.android.foodrecipe.domain.model.Step
import com.degea9.foodrecipe.remote.response.StepResponse

class StepDataListMapper : ListMapper<StepResponse, Step> {
    override fun map(input: List<StepResponse>?): List<Step>? {
        return input?.map {
            Step(
                number = it.number,
                step = it.step,
                ingredients = MapperFactory.createListMapper<IngredientDataListMapper>().map(it.ingredients)
                )
        }
    }
}