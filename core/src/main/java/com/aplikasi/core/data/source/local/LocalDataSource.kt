package com.aplikasi.core.data.source.local

import com.aplikasi.core.data.source.local.entity.MovieEntity
import com.aplikasi.core.data.source.local.entity.MovieSearchEntity
import com.aplikasi.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(val movieDao: MovieDao) {

    fun getListMovie(): Flow<List<MovieEntity>> = movieDao.getAllMovie()

    suspend fun insertMovie(movieList: List<MovieEntity>) = movieDao.insertMovie(movieList)

    fun getListMovieSearch(search:String): Flow<List<MovieSearchEntity>> = movieDao.getAllMovieSearch(search)

    suspend fun insertMovieSearch(movieList: List<MovieSearchEntity>) = movieDao.insertMovieSearch(movieList)

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovie()

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }

}