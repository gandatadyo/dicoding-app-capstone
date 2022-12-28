package com.aplikasi.core.data.source.local.room

import androidx.room.*
import com.aplikasi.core.data.source.local.entity.MovieEntity
import com.aplikasi.core.data.source.local.entity.MovieSearchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getAllMovie(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<MovieEntity>)

    @Query("SELECT * FROM movie_search WHERE Name LIKE '%' || :search || '%' ")
    fun getAllMovieSearch(search:String): Flow<List<MovieSearchEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieSearch(movie: List<MovieSearchEntity>)

    @Query("SELECT * FROM movie where isFavorite = 1")
    fun getFavoriteMovie(): Flow<List<MovieEntity>>

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)
}