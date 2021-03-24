package com.degea9.android.foodrecipe.repository.mapper

import com.degea9.android.foodrecipe.domain.model.*
import com.degea9.foodrecipe.remote.response.*

class IngredientDataListMapper:ListMapper<IngredientResponse,Ingredient> {
    override fun map(input: List<IngredientResponse>?): List<Ingredient>? {
        return input?.map {
            Ingredient(
                id = it.id,
                name = it.name,
                localizedName = it.localizedName,
                image = it.image,
                amount = it.amount,
                unit = it.unit,
                measures = MapperFactory.createMapper<IngredientMeasureMapper>().map(it.measures)
            )
        }
    }
}

class IngredientMeasureMapper: Mapper<IngredientMeasureResponse?, IngredientMeasure?> {
    override fun map(input: IngredientMeasureResponse?): IngredientMeasure {
        return IngredientMeasure(
                us = MapperFactory.createMapper<UsMeasureMapper>().map(input?.us),
                metric = MapperFactory.createMapper<MetricMeasureMapper>().map(input?.metric)
            )
    }

}
class UsMeasureMapper:Mapper<UsMeasureResponse?, UsMeasure?> {
    override fun map(input: UsMeasureResponse?): UsMeasure {
        return UsMeasure(
            amount = input?.amount,
            unitShort = input?.unitShort,
            unitLong = input?.unitShort
        )
    }

}
class MetricMeasureMapper: Mapper<MetricMeasureResponse?, MetricMeasure?> {
    override fun map(input: MetricMeasureResponse?): MetricMeasure  {
        return MetricMeasure(
            amount = input?.amount,
            unitShort = input?.unitShort,
            unitLong = input?.unitShort
        )
    }

}