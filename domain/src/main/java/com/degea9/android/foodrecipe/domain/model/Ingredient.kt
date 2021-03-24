package com.degea9.android.foodrecipe.domain.model

data class Ingredient(

    val id: Int?,

    val name: String?,

    val localizedName: String?,

    val image: String?,

    val amount: Float?,

    val unit: String?,

    val measures: IngredientMeasure?
)

data class IngredientMeasure(
    val us: UsMeasure?,
    val metric: MetricMeasure?
)

data class UsMeasure(
    val amount: Float?,
    val unitShort: String?,
    val unitLong: String?
)

data class MetricMeasure(
    val amount: Float?,
    val unitShort: String?,
    val unitLong: String?
)