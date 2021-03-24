package com.degea9.foodrecipe.remote.response

data class IngredientResponse(

    val id: Int?,

    val name: String?,

    val localizedName: String?,

    val image: String?,

    val amount: Float?,

    val unit: String?,

    val measures: IngredientMeasureResponse?
)

data class IngredientMeasureResponse(
    val us: UsMeasureResponse?,
    val metric: MetricMeasureResponse?
)

data class UsMeasureResponse(
    val amount: Float?,
    val unitShort: String?,
    val unitLong: String?
)

data class MetricMeasureResponse(
    val amount: Float?,
    val unitShort: String?,
    val unitLong: String?
)