package com.aplikasi.appcapstone.menu_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aplikasi.core.data.MovieRepository
import com.aplikasi.core.data.source.local.entity.MovieEntity
import com.aplikasi.core.domain.model.Movie
import com.aplikasi.core.domain.usecase.IMovieUseCase

class DetailMovieViewModel(private val movieRepository: IMovieUseCase):ViewModel() {
    val movieData = MutableLiveData<Movie>(  )

    fun setFavoriteMovie(movie: Movie) {
        val newStatus =  !movie.isFavorite
        movieRepository.setFavoriteMovie(movie,newStatus)
        movie.isFavorite = newStatus
        movieData.value = movie
    }
}