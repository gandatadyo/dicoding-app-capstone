package com.aplikasi.core.domain.usecase

import com.aplikasi.core.data.Resource
import com.aplikasi.core.domain.model.Movie
import com.aplikasi.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor constructor(
    private val movieRepository : IMovieRepository
):IMovieUseCase {

    companion object {
        @Volatile
        private var instance: MovieInteractor? = null

        fun getInstance(
            movieRepository: IMovieRepository,
        ): MovieInteractor =
            instance ?: synchronized(this) {
                instance ?: MovieInteractor(movieRepository)
            }
    }

    override fun getListMovie(): Flow<Resource<List<Movie>>> {
        return movieRepository.getListMovie()
    }

    override fun getSearchMovie(search:String): Flow<Resource<List<Movie>>> {
        return movieRepository.getSearchMovie(search)
    }

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return movieRepository.getFavoriteMovie()
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        return movieRepository.setFavoriteMovie(movie,state)
    }
}