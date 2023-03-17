package net.aedev.movieapp.base.api.datahandling

data class Result<out T>(
    val data: T?,
    val error: Throwable?
)
