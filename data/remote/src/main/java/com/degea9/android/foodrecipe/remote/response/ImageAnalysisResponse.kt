package com.degea9.android.foodrecipe.remote.response

import com.degea9.foodrecipe.remote.response.RecipeResponse

data class ImageAnalysisResponse(
        val category: CategoryResponse?,
        val recipes: List<RecipeResponse>?
)