package com.aplikasi.core.data.source.remote.network

import com.aplikasi.core.data.source.remote.RemoteDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiConfig {

//    companion object {
//        @Volatile
//        private var instance: ApiConfig? = null
//
//        fun getInstance(): ApiConfig =
//            instance ?: synchronized(this) {
//                instance ?: ApiConfig()
//            }
//    }

    fun resultObject(): ApiService {
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
//            .connectTimeout(120, TimeUnit.SECONDS)
//            .readTimeout(120, TimeUnit.SECONDS)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }

}