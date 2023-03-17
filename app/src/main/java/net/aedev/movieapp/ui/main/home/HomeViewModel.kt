package net.aedev.movieapp.ui.main.home

import androidx.lifecycle.MutableLiveData
import net.aedev.movieapp.base.api.datahandling.ResultsObserver
import net.aedev.movieapp.base.model.Error
import net.aedev.movieapp.base.model.MovieResponse
import net.aedev.movieapp.base.repository.ApiRepository
import net.aedev.movieapp.base.ui.BaseViewModel
import net.aedev.movieapp.base.ultils.extentions.plusAssign

class HomeViewModel(private val apiApi: ApiRepository) : BaseViewModel() {
    private val getResponseData = MutableLiveData<MovieResponse>()
    private val error = MutableLiveData<Error>()

    fun getResponseData() = getResponseData
    fun error() = error


    fun fetchMovies(keyword: String, page: Int) {
        compositeDisposable += apiApi.fetchMovie(keyword, page)
            .subscribeWith(MovieObserver())
    }

    private inner class MovieObserver : ResultsObserver<MovieResponse>() {
        override fun onSuccess(response: MovieResponse) {
            getResponseData.postValue(response)
        }

        override fun onError(err: Error) {
            error.postValue(err)
        }
    }
}