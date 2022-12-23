package com.aplikasi.appcapstone.menu_search_movie

import androidx.lifecycle.*
import com.aplikasi.core.data.MovieRepository
import com.aplikasi.core.data.Resource
import com.aplikasi.core.data.source.local.entity.MovieEntity
import com.aplikasi.core.data.source.remote.network.ApiResponse
import com.aplikasi.core.data.source.remote.response.ResultsMovie
import com.aplikasi.core.domain.usecase.IMovieUseCase

class SearchMovieViewModel(movieRepository: IMovieUseCase):ViewModel() {
    var search = MutableLiveData( "" )
    val movieList = Transformations.switchMap(search){
        query -> movieRepository.getSearchMovie(query).asLiveData()
    }
}