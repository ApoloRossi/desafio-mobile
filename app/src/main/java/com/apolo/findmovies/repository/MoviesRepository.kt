package com.apolo.findmovies.repository

import com.apolo.findmovies.repository.model.MoviesResponse
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import java.lang.Exception

class MoviesRepository(private val moviesRemoteDataSource: MoviesRemoteDataSource) {

    suspend fun getUpcomingMovies() : MoviesResponse? {
        return withContext(IO){
            try {
                moviesRemoteDataSource.getUpcomingMovies()
            } catch (exception : Exception) {
                throw exception
            }
        }
    }

    suspend fun getPopularMovies() : MoviesResponse? {
        return withContext(IO){
            try {
                moviesRemoteDataSource.getPopularMovies()
            } catch (exception : Exception) {
                throw exception
            }
        }
    }
}