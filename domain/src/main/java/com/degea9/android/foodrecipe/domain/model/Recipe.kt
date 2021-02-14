package com.degea9.android.foodrecipe.domain.model

data class Recipe(

    val id: Int,

    val title: String?,

    val summary: String?,

    val image: String?,

    val imageType: String?,

    val sourceName: String?,

    val dishTypes: List<String>?,

    val analyzedInstructions: List<Instruction>?
)