package net.aedev.movieapp.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.*
import net.aedev.movieapp.R
import net.aedev.movieapp.base.model.Movie
import net.aedev.movieapp.ui.main.home.adapter.MovieAdapter
import net.aedev.movieapp.ui.main.home.adapter.MovieListener
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.scope.viewModel

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private var movieAdapter: MovieAdapter? = null
    private var currentPage = 1
    private var movieListener = object : MovieListener {
        override fun onItemClick(dataMovie: Movie, position: Int) {

        }

        override fun onLoadMore(pageNumber: Int) {
            currentPage = pageNumber
            loadMoreData(pageNumber)
        }
    }

    private fun loadMoreData(pageNumber: Int) {
        val keyWord = search?.query?.toString() ?: ""
        homeViewModel.fetchMovies(keyWord, pageNumber)
    }

    private val homeViewModel: HomeViewModel by currentScope.viewModel(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        handleObservable()
        homeViewModel.fetchMovies("Marvel", currentPage)
    }

    private fun handleObservable() {
        homeViewModel.getResponseData().observe(viewLifecycleOwner) { movieResponse ->
            movieResponse.Search?.let {
                movieAdapter?.setMoreMovies(it, currentPage)
            }
        }
        homeViewModel.error().observe(viewLifecycleOwner) {
            movieAdapter?.setIsLoadingMore(false)
        }
    }

    private fun initView() {
        activity?.title = getString(R.string.home_title)
        search.setQuery(getString(R.string.query_hint), false)
        movieAdapter = context?.let { MovieAdapter(it, rcv_movie) }
        rcv_movie.adapter = movieAdapter
        movieAdapter?.setMovieListener(movieListener)
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                movieAdapter?.cleanDataMovies()
                currentPage = 1
                search?.clearFocus()
                val keyWord = search?.query?.toString() ?: ""
                homeViewModel.fetchMovies(keyWord, currentPage)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

}