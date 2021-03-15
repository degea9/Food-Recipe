package com.degea9.android.foodrecipe.remote.di

import com.degea9.android.foodrecipe.remote.BuildConfig
import com.degea9.android.foodrecipe.remote.datasource.*
import com.degea9.android.foodrecipe.remote.interceptor.ApiInterceptor
import com.degea9.foodrecipe.remote.FoodRecipeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    private const val CLIENT_TIME_OUT = 120L


    @Singleton
    @Provides
    @IntoSet
    fun provideHttpLoggingInterceptor():Interceptor{
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if(BuildConfig.DEBUG){
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
        return httpLoggingInterceptor
    }

    @Singleton
    @Provides
    @IntoSet
    fun provideApiInterceptor():Interceptor = ApiInterceptor()

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptors:MutableSet<Interceptor>):OkHttpClient{
        val okHttpBuilder = OkHttpClient.Builder()
        interceptors.forEach {
            okHttpBuilder.addInterceptor(it)
        }
        okHttpBuilder
            .connectTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
        return okHttpBuilder.build()
    }

    @Singleton
    @Provides
    fun provideFoodRecipeService(okHttpClient: OkHttpClient): FoodRecipeService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL_API)
            .build()
            .create(FoodRecipeService::class.java)
    }

    @Singleton
    @Provides
    fun provideRecipeRemoteDataSource(foodRecipeService: FoodRecipeService): RecipeRemoteDataSource{
        return RecipeRemoteDataSourceImpl(foodRecipeService)
    }

    @Singleton
    @Provides
    fun provideSuggestionKeywordRemoteDataSourece(foodRecipeService: FoodRecipeService): SuggestionKeywordRemoteDataSource{
        return SuggestionKeywordRemoteDataSourceImpl(foodRecipeService)
    }

    @Singleton
    @Provides
    fun provideFoodRemoteDataSource(foodRecipeService: FoodRecipeService): FoodRemoteDataSource{
        return FoodRemoteDataSourceImpl(foodRecipeService)
    }

}