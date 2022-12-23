package com.aplikasi.core.domain.repository

import com.aplikasi.core.data.Resource
import com.aplikasi.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getListMovie(): Flow<Resource<List<Movie>>>

    fun getSearchMovie(string:String): Flow<Resource<List<Movie>>>

    fun getFavoriteMovie(): Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)

}