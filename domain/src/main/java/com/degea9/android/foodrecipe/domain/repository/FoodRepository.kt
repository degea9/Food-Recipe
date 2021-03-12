package com.degea9.android.foodrecipe.domain.repository

import android.net.Uri
import com.degea9.android.foodrecipe.domain.model.ImageAnalysis


interface FoodRepository {
    suspend fun scanImage(image: Uri): ImageAnalysis?
}