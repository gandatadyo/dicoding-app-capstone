package com.aplikasi.core.data.source.remote.network

import com.aplikasi.core.data.source.remote.response.ResponseDetailMovie
import com.aplikasi.core.data.source.remote.response.ResponseListMovie
import com.aplikasi.core.data.source.remote.response.ResponseSearchMovie
import retrofit2.Call
import retrofit2.http.*

const val apiKey = "319749e5cae98cd2ad274466181160d2"

interface ApiService {

    @GET("discover/movie?api_key=$apiKey")
    suspend fun listMovie(): ResponseListMovie

    @GET("search/movie?api_key=$apiKey")
    suspend fun searchMovie(
        @Query("query") query:String,
    ): ResponseSearchMovie

    @GET("movie/{id_movie}?api_key=$apiKey")
    fun detailMovie(
        @Path("id_movie") id_movie:String,
    ): Call<ResponseDetailMovie>

}