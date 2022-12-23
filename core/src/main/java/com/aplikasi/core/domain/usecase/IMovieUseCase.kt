package com.aplikasi.core.domain.usecase

import com.aplikasi.core.data.Resource
import com.aplikasi.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieUseCase {
    fun getListMovie(): Flow<Resource<List<Movie>>>
    fun getSearchMovie(search:String): Flow<Resource<List<Movie>>>
    fun getFavoriteMovie(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}