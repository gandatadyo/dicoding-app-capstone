package com.aplikasi.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aplikasi.core.data.source.remote.network.ApiConfig
import com.aplikasi.core.data.source.remote.network.ApiResponse
import com.aplikasi.core.data.source.remote.network.ApiService
import com.aplikasi.core.data.source.remote.response.ResponseDetailMovie
import com.aplikasi.core.data.source.remote.response.ResponseListMovie
import com.aplikasi.core.data.source.remote.response.ResultsMovie
import com.aplikasi.core.data.source.remote.response.ResultsMovieSearch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource constructor(val apiConfig: ApiConfig) {

    suspend fun getListMovie(): Flow<ApiResponse<List<ResultsMovie>>> {
        return flow {
            try {
                val response = apiConfig.resultObject().listMovie()
                val dataArray = response.results
                if(dataArray!=null) {
                    if (dataArray.isNotEmpty()) {
                        emit(ApiResponse.Success(response.results))
                    } else {
                        emit(ApiResponse.Empty)
                    }
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSearchMovie(search:String): Flow<ApiResponse<List<ResultsMovieSearch>>> {
        return flow {
            try {
                val response = apiConfig.resultObject().searchMovie(search)
                val dataArray = response.results
                if(dataArray!=null) {
                    if (dataArray.isNotEmpty()) {
                        emit(ApiResponse.Success(response.results))
                    } else {
                        emit(ApiResponse.Empty)
                    }
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}