package com.degea9.foodrecipe.remote.response

data class StepResponse(

    val number: Int?,

    val step: String?,

    val ingredients: List<IngredientResponse>?
)