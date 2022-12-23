package com.aplikasi.favorite.di

import com.aplikasi.appcapstone.menu_detail.DetailMovieViewModel
import com.aplikasi.appcapstone.menu_movie.MovieViewModel
import com.aplikasi.favorite.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { FavoriteViewModel(get()) }
}