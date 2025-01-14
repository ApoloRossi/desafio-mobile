package com.apolo.findmovies.presentation.home.viewModel

import androidx.lifecycle.LiveData
import com.apolo.findmovies.base.connection.ConnectionUseCase
import com.apolo.findmovies.base.resources.LiveDataResource
import com.apolo.findmovies.base.resources.Resource
import com.apolo.findmovies.data.model.BaseViewModel
import com.apolo.findmovies.data.model.MovieViewModel
import com.apolo.findmovies.repository.MoviesRepository
import com.apolo.findmovies.repository.UseCaseErrorCode

class HomeViewModel(private val moviesRepository: MoviesRepository) : BaseViewModel() {

    private val moviesLivedata = LiveDataResource<List<MovieViewModel>>()

    fun onViewReady() {
        getUpcomingMovies()
        getGenres()
    }

    fun onCategoryChange(isUpcoming: Boolean) {
        if (isUpcoming) {
            getUpcomingMovies()
        } else {
            getPopularMovies()
        }
    }

    fun onReachedEndOfPage(isUpcoming: Boolean, nextPage : Int) {
        if (isUpcoming) {
            getUpcomingMovies(nextPage)
        } else {
            getPopularMovies(nextPage)
        }
    }

    private fun getUpcomingMovies(nextPage : Int = 1) = runCoroutine {
        ConnectionUseCase.testInternetConnection()
        moviesLivedata.postValue(Resource.loading())

        moviesRepository.getUpcomingMovies(nextPage)?.let { moviesResponse ->

            if (moviesResponse.movies.isNotEmpty()) {
                moviesLivedata.postValue(Resource.success(moviesResponse.toViewModelList(), currentPage = moviesResponse.page, lastPage = moviesResponse.total_pages))
            }
        }
    }.onError {
        moviesLivedata.postValue(Resource.error(errorCode = it.userCaseErrorCode.errorCode))
    }

    private fun getPopularMovies(nextPage : Int = 1) = runCoroutine {
        ConnectionUseCase.testInternetConnection()
        moviesLivedata.postValue(Resource.loading())

        moviesRepository.getPopularMovies(nextPage)?.let { moviesResponse ->

            if (moviesResponse.movies.isNotEmpty()) {
                moviesLivedata.postValue(Resource.success(moviesResponse.toViewModelList(), currentPage = moviesResponse.page, lastPage = moviesResponse.total_pages))
            }
        }
    }.onError {
        moviesLivedata.postValue(Resource.error(errorCode = it.userCaseErrorCode.errorCode))
    }

    private fun getGenres() = runCoroutine {
        ConnectionUseCase.testInternetConnection()
        moviesRepository.getGenres()
    }.onError {

    }

    fun getMoviesLiveData() = moviesLivedata as LiveData<Resource<List<MovieViewModel>>>

}