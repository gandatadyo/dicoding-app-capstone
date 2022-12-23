package com.aplikasi.appcapstone.di

import com.aplikasi.appcapstone.menu_detail.DetailMovieViewModel
import com.aplikasi.appcapstone.menu_movie.MovieViewModel
import com.aplikasi.appcapstone.menu_search_movie.SearchMovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
    viewModel { SearchMovieViewModel(get()) }
}