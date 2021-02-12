package com.degea9.foodrecipe.remote.response

data class InstructionResponse(

    val name: String?,

    val steps: List<StepResponse>?
)