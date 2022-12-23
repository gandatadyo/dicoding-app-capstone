package com.aplikasi.appcapstone.menu_movie

import androidx.lifecycle.*
import com.aplikasi.core.data.MovieRepository
import com.aplikasi.core.data.Resource
import com.aplikasi.core.data.source.local.entity.MovieEntity
import com.aplikasi.core.domain.usecase.IMovieUseCase

class MovieViewModel(movieRepository: IMovieUseCase):ViewModel() {
    val movieList = movieRepository.getListMovie().asLiveData()
}