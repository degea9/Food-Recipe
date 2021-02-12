package com.degea9.foodrecipe.remote.response

data class RecipeResponse(

    val id: Int,

    val title: String?,

    val summary: String?,

    val image: String?,

    val imageType: String?,

    val sourceName: String?,

    val dishTypes: List<String>?,

    val analyzedInstructions: List<InstructionResponse>?
)