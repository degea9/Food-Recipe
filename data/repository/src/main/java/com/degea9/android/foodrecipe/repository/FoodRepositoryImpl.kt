package com.degea9.android.foodrecipe.repository

import android.net.Uri
import com.degea9.android.foodrecipe.domain.model.ImageAnalysis
import com.degea9.android.foodrecipe.domain.repository.FoodRepository
import com.degea9.android.foodrecipe.remote.datasource.FoodRemoteDataSource
import com.degea9.android.foodrecipe.repository.mapper.DataMappersFacade
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import timber.log.Timber
import java.io.File

import javax.inject.Inject


class FoodRepositoryImpl @Inject constructor(private val foodRemoteDataSource: FoodRemoteDataSource,
                                             private val dataMappersFacade: DataMappersFacade) : FoodRepository {
    override suspend fun scanImage(image: Uri): ImageAnalysis? {
        try {
            image.path?.let {

                val file = File(it)
                return if(file.exists() && file.canRead() && file.canWrite()){
                    val requestFile = file.asRequestBody("application/octet-stream".toMediaTypeOrNull())
                    val body = MultipartBody.Part.createFormData("file", file.name, requestFile)
                    dataMappersFacade.mapRemoteImageAnalysisToDomain(foodRemoteDataSource.scanImage(body))
                } else null
            }?: return null
        }
        catch (t: Throwable){
            Timber.d("POST_IMAGE: ${t.message} ")
            return null
        }

    }
}