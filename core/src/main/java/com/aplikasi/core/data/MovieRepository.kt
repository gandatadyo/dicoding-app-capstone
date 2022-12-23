package com.aplikasi.core.data

import com.aplikasi.core.data.source.local.LocalDataSource
import com.aplikasi.core.data.source.remote.RemoteDataSource
import com.aplikasi.core.data.source.remote.network.ApiResponse
import com.aplikasi.core.data.source.remote.response.ResultsMovie
import com.aplikasi.core.data.source.remote.response.ResultsMovieSearch
import com.aplikasi.core.domain.model.Movie
import com.aplikasi.core.domain.repository.IMovieRepository
import com.aplikasi.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: com.aplikasi.core.utils.AppExecutors
):IMovieRepository {

    override fun getListMovie(): Flow<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<ResultsMovie>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getListMovie().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsMovie>>> =
                remoteDataSource.getListMovie()

            override suspend fun saveCallResult(data: List<ResultsMovie>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()
    }

    override fun getSearchMovie(search:String): Flow<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<ResultsMovieSearch>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getListMovieSearch(search).map {
                    DataMapper.mapEntitiesSearchToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = true // data == null || data.isEmpty()
                // true // ganti dengan true jika ingin selalu mengambil data dari internet

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsMovieSearch>>> {
                return remoteDataSource.getSearchMovie(search)
            }

            override suspend fun saveCallResult(data: List<ResultsMovieSearch>) {
                val movieList = DataMapper.mapResponsesToEntitiesMovieSearch(data)
                localDataSource.insertMovieSearch(movieList)
            }
        }.asFlow()
    }

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map {
           DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }

}