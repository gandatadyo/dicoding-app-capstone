package com.aplikasi.core.utils

import com.aplikasi.core.data.source.local.entity.MovieEntity
import com.aplikasi.core.data.source.local.entity.MovieSearchEntity
import com.aplikasi.core.data.source.remote.response.ResultsMovie
import com.aplikasi.core.data.source.remote.response.ResultsMovieSearch
import com.aplikasi.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(input: List<ResultsMovie>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                IDMovie = it.id.toString(),
                Name = it.originalTitle.toString(),
                Description = it.overview.toString(),
                Img = it.backdropPath.toString(),
                isFavorite = false,
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapResponsesToEntitiesMovieSearch(input: List<ResultsMovieSearch>): List<MovieSearchEntity> {
        val movieList = ArrayList<MovieSearchEntity>()
        input.map {
            val movie = MovieSearchEntity(
                IDMovie = it.id.toString(),
                Name = it.originalTitle.toString(),
                Description = it.overview.toString(),
                Img = it.backdropPath.toString(),
                isFavorite = false,
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                idMovie = it.IDMovie.toString(),
                description = it.Description.toString(),
                name = it.Name.toString(),
                image = it.Img.toString(),
                isFavorite = it.isFavorite!!
            )
        }


    fun mapEntitiesSearchToDomain(input: List<MovieSearchEntity>): List<Movie> =
        input.map {
            Movie(
                idMovie = it.IDMovie.toString(),
                description = it.Description.toString(),
                name = it.Name.toString(),
                image = it.Img.toString(),
                isFavorite = it.isFavorite!!
            )
        }

    fun mapDomainToEntity(input: Movie): MovieEntity =
        MovieEntity(
            IDMovie = input.idMovie,
            Description = input.description.toString(),
            Name = input.name.toString(),
            Img = input.image.toString(),
            isFavorite = input.isFavorite!!
        )
}