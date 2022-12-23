package com.aplikasi.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aplikasi.core.data.MovieRepository
import com.aplikasi.core.domain.repository.IMovieRepository
import com.aplikasi.core.domain.usecase.IMovieUseCase

class FavoriteViewModel(movieRepository: IMovieUseCase):ViewModel() {
    val movieList = movieRepository.getFavoriteMovie().asLiveData()
}