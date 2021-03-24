package com.degea9.android.foodrecipe.domain.model

data class Step(

    val number: Int?,

    val step: String?,

    val ingredients: List<Ingredient>?,

    val equipment: List<Equipment>?
)