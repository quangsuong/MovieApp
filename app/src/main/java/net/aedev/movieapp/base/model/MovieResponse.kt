package net.aedev.movieapp.base.model

data class MovieResponse(
    val Search: MutableList<Movie>?,
    val totalResults: String?,
    val Response: String?
)

data class Movie(
    val Title: String?,
    val Year: String?,
    val imdbID: String?,
    val Type: String?,
    val Poster: String?
)