package com.degea9.foodrecipe.remote.response

import com.degea9.android.foodrecipe.remote.response.EquipmentResponse

data class StepResponse(

    val number: Int?,

    val step: String?,

    val ingredients: List<IngredientResponse>?,

    val equipment: List<EquipmentResponse>?
)