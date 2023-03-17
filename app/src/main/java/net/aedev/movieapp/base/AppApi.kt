package net.aedev.movieapp.base

import io.reactivex.Observable
import net.aedev.movieapp.base.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AppApi {

    @GET("/?apikey=b9bd48a6&type=movie")
    fun fetchMovies(
        @Query("s") keyword: String,
        @Query("page") page: Int
    ): Observable<MovieResponse>
}