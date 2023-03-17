package net.aedev.movieapp.base.repository

import io.reactivex.Observable
import net.aedev.movieapp.base.AppApi
import net.aedev.movieapp.base.api.datahandling.Result
import net.aedev.movieapp.base.api.datahandling.retrofitResponseToResult
import net.aedev.movieapp.base.model.MovieResponse
import net.aedev.movieapp.base.ultils.rx.AppReactivexSchedulers

class ApiRepository(
    private val appApi: AppApi,
    private val rxSchedulers: AppReactivexSchedulers
) {
    fun fetchMovie(keyword: String, page: Int): Observable<Result<MovieResponse>> {
        return appApi.fetchMovies(keyword, page)
            .retrofitResponseToResult()
            .subscribeOn(rxSchedulers.io())
            .observeOn(rxSchedulers.androidMainThread())
    }
}
