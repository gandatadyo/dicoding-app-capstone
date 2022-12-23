package com.aplikasi.core.di

import com.aplikasi.core.data.MovieRepository
import com.aplikasi.core.data.source.local.LocalDataSource
import com.aplikasi.core.data.source.local.room.AppDatabase
import com.aplikasi.core.data.source.remote.RemoteDataSource
import com.aplikasi.core.data.source.remote.network.ApiConfig
import com.aplikasi.core.domain.repository.IMovieRepository
import com.aplikasi.core.domain.usecase.IMovieUseCase
import com.aplikasi.core.domain.usecase.MovieInteractor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        AppDatabase.getInstance(androidContext()).movieDao()
    }
}

val networkModule = module {
    single {
        ApiConfig()
    }
}

val useCaseModule = module {
    single<IMovieUseCase> { MovieInteractor(get())  }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { com.aplikasi.core.utils.AppExecutors() }
    single<IMovieRepository> { MovieRepository(get(),get(),get()) }
}