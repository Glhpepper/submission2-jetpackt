package com.example.moviescatalogue.utils

import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviescatalogue.R
import com.example.moviescatalogue.data.local.entity.FavoriteMovies
import com.example.moviescatalogue.data.local.entity.FavoriteShows
import com.example.moviescatalogue.data.local.entity.MoviesEntity
import com.example.moviescatalogue.data.local.entity.TvShowsEntity
import com.example.moviescatalogue.data.remote.api.RetrofitClient.BASE_IMG
import com.example.moviescatalogue.data.remote.response.GenresItemMovies
import com.example.moviescatalogue.data.remote.response.GenresItemShows
import com.example.moviescatalogue.data.remote.response.SeasonsItem
import com.example.moviescatalogue.ui.detail.shows.SeasonAdapter
import com.example.moviescatalogue.ui.favorite.movies.FavoriteMoviesAdapter
import com.example.moviescatalogue.ui.favorite.tvshows.FavoriteShowsAdapter
import com.example.moviescatalogue.ui.main.movies.MoviesAdapter
import com.example.moviescatalogue.ui.main.tvshows.TvShowsAdapter

@BindingAdapter("moviesList")
fun bindMoviesList(recyclerView: RecyclerView, data: List<MoviesEntity>?) {
    val adapter = recyclerView.adapter as MoviesAdapter
    adapter.setMovies(data)
}

@BindingAdapter("favoriteMoviesList")
fun bindMoviesFavorite(recyclerView: RecyclerView, data: PagedList<FavoriteMovies>?) {
    val adapter = recyclerView.adapter as FavoriteMoviesAdapter
    adapter.submitList(data)
}

@BindingAdapter("showsList")
fun bindShowsList(recyclerView: RecyclerView, data: List<TvShowsEntity>?) {
    val adapter = recyclerView.adapter as TvShowsAdapter
    adapter.setTvShows(data)
}

@BindingAdapter("favoriteShowsList")
fun bindShowsFavorite(recyclerView: RecyclerView, data: PagedList<FavoriteShows>?) {
    val adapter = recyclerView.adapter as FavoriteShowsAdapter
    adapter.submitList(data)
}

@BindingAdapter("seasonList")
fun bindSeasonList(recyclerView: RecyclerView, data: List<SeasonsItem>?) {
    val adapter = recyclerView.adapter as SeasonAdapter
    adapter.setListSeason(data)
}

@BindingAdapter("progressScore")
fun bindProgress(progressBar: ProgressBar, string: String?) {
    val pbScore = string?.toDouble()?.times(10)
    if (pbScore != null) {
        progressBar.progress = pbScore.toInt()
    }
}

@BindingAdapter("imgUrl")
fun bindImage(imageView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Glide.with(imageView.context)
            .load(BASE_IMG + imgUrl)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imageView)
    }
}

@BindingAdapter("genreTextMovies")
fun convertGenreMovies(textView: TextView, data: List<GenresItemMovies>?) {
    val genre = data?.map {
        it.name
    }
    textView.text = genre?.joinToString(separator = " - ")
}

@BindingAdapter("genreTextShows")
fun convertGenreShows(textView: TextView, data: List<GenresItemShows>?) {
    val genre = data?.map {
        it.name
    }
    textView.text = genre?.joinToString(separator = " - ")
}