package net.aedev.movieapp.ui.main.home.adapter

import net.aedev.movieapp.base.model.Movie

interface MovieListener {
    fun onItemClick(dataMovie: Movie, position: Int)
    fun onLoadMore(pageNumber: Int)
}