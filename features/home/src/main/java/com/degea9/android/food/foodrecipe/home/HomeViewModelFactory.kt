//package com.degea9.android.food.foodrecipe.home
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.degea9.android.foodrecipe.domain.GetPopularRecipeUsecase
//import javax.inject.Inject
//
//class HomeViewModelFactory @Inject constructor(
//    private val getPopularRecipeUsecase: GetPopularRecipeUsecase
//) : ViewModelProvider.Factory {
//
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//
//        if (modelClass != HomeViewModel::class.java) {
//            throw IllegalArgumentException("Unknown ViewModel class")
//        }
//
//        return HomeViewModel(
//            getPopularRecipeUsecase
//        ) as T
//    }
//}