package com.degea9.android.food.foodrecipe.scan

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.degea9.android.foodrecipe.core.BaseViewModel
import com.degea9.android.foodrecipe.domain.model.ImageAnalysis
import com.degea9.android.foodrecipe.domain.repository.FoodRepository
import com.degea9.android.foodrecipe.domain.scan.ScanImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScanViewModel @Inject constructor(private val scanImageUseCase: ScanImageUseCase): BaseViewModel() {

    private var _imageAnalysisLiveData = MutableLiveData<ImageAnalysis>()
    var imageAnalysisLiveData: LiveData<ImageAnalysis> = _imageAnalysisLiveData

    fun scanImage(uri: Uri){
        viewModelScope.launch(Dispatchers.IO) {
            val result = scanImageUseCase.scanImage(uri)
            Log.d("AAAAAA", result.toString())
            result?.let {
                _imageAnalysisLiveData.postValue(it)
            }
        }
    }
}