package com.apolo.findmovies.base.resources

import androidx.lifecycle.MutableLiveData

data class Resource<out DataType>(
    val status : Status,
    val data: DataType? = null,
    val message: String? = null,
    val currentPage: Int? = null,
    val lastPage: Int? = null,
    val errorCode: Int? = null
) {

    companion object {
        fun <DataType> loading(data: DataType? = null) : Resource<DataType> = Resource(Status.LOADING, data = data)
        fun <DataType> loadingNextPage(data: DataType? = null) : Resource<DataType> = Resource(Status.LOADING_NEXT_PAGE, data = data)
        fun <DataType> success(data: DataType? = null, currentPage: Int? = null, lastPage: Int? = null) : Resource<DataType> = Resource(Status.SUCCESS, data = data, currentPage = currentPage, lastPage = lastPage)
        fun <DataType> error(data: DataType? = null, errorMessage : String? = null, errorCode : Int? = null) : Resource<DataType> = Resource(Status.ERROR, data = data, message = errorMessage, errorCode = errorCode)
    }

    enum class Status {
        SUCCESS,
        SUCCESS_NEXT_PAGE,
        LOADING,
        LOADING_NEXT_PAGE,
        ERROR,
        ERROR_NEXT_PAGE
    }

}

typealias LiveDataResource<DataType> = MutableLiveData<Resource<DataType>>
