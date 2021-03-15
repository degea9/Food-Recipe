package com.degea9.android.foodrecipe.domain.scan

import android.net.Uri
import com.degea9.android.foodrecipe.domain.model.ImageAnalysis
import com.degea9.android.foodrecipe.domain.repository.FoodRepository
import javax.inject.Inject

class ScanImageUseCase @Inject constructor(private val repository: FoodRepository) {
    suspend fun scanImage(uri: Uri): ImageAnalysis?{
        return repository.scanImage(uri)
    }
}