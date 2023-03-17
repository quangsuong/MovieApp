package net.aedev.movieapp.ui.main.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*
import net.aedev.movieapp.R
import net.aedev.movieapp.base.model.Movie

class MovieAdapter(
    private val context: Context,
    recyclerView: RecyclerView
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movieListener: MovieListener? = null
    private var movies: MutableList<Movie> = mutableListOf()
    private var currentPage: Int = 0
    private var isEndOfList: Boolean = false
    private var isLoadingMore: Boolean = false

    init {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && !isEndOfList && !isLoadingMore) {
                    movieListener?.onLoadMore(currentPage + 1)
                    isLoadingMore = true
                }
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val root = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(root)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.movieTitle.text = movie.Title
        holder.movieReleaseYear.text = movie.Year
        Glide.with(context).load(movie.Poster)
            .error(ContextCompat.getDrawable(context, R.drawable.placeholder))
            .placeholder(ContextCompat.getDrawable(context, R.drawable.placeholder))
            .into(holder.thumbnail)
        holder.root.setOnClickListener {
            movieListener?.onItemClick(movie, position)
        }
    }

    fun setMoreMovies(movies: MutableList<Movie>, currentPage: Int) {
        if (this.currentPage == currentPage) return
        if (movies.isEmpty() && this.currentPage != 0) {
            this.isEndOfList = true
            return
        }
        this.movies.addAll(movies)
        this.currentPage = currentPage
        this.isLoadingMore = false
        notifyDataSetChanged()
    }

    fun setIsLoadingMore(isLoading: Boolean) {
        this.isLoadingMore = isLoading
    }

    fun setMovieListener(movieListener: MovieListener) {
        this.movieListener = movieListener
    }

    fun cleanDataMovies() {
        movies.clear()
        currentPage = 0
        isEndOfList = false
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(view: View) : ViewHolder(view) {
        val root: CardView = view.root
        val movieTitle: TextView = view.tv_movie_title
        val movieReleaseYear: TextView = view.tv_movie_release_year
        val thumbnail: ImageView = view.thumbnail

    }
}