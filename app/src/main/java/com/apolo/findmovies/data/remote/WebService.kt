package com.apolo.findmovies.data.remote

import com.apolo.findmovies.repository.model.GenresResponse
import com.apolo.findmovies.repository.model.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET

interface WebService {

    @GET("movie/upcoming")
    fun getUpcomingMovies() : Call<MoviesResponse>

    @GET("movie/popular")
    fun getPopularMovies() : Call<MoviesResponse>

    //TODO: To populate the generes of movie detail
    @GET("genre/movie/list")
    fun getMoviesGenres() : Call<GenresResponse>

    //TODO: To populate movie detail adapter
    @GET("/movie/{movie_id}/credits")
    fun getCredits() : Call<Unit>

    //TODO: To get configurations and base urls to laod images.
    @GET("configuration")
    fun getConfiguration() : Call<Unit>

}