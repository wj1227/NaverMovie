package com.jay.navermovie.utils

import android.text.Html
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jay.navermovie.R
import com.jay.navermovie.data.search.Movie
import com.jay.navermovie.ui.search.MovieSearchAdapter

@BindingAdapter("urlImage")
fun setUrlImage(iv: ImageView, url: String) {
    Glide.with(iv).load(url)
        .placeholder(R.drawable.ic_default)
        .into(iv)
}

@BindingAdapter("htmlText")
fun setHtmlText(tv: TextView, html: String) {
    HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT)
}

@BindingAdapter("movieRating")
fun setMovieRating(rb: RatingBar, score: String) {
    rb.rating = (score.toFloatOrNull() ?: 0f) / 2
}

@BindingAdapter("setItems")
fun RecyclerView.setAdapterItems(item: List<Movie>?) {
    with((adapter as MovieSearchAdapter)) {
        this.clear()
        item?.let { this.setItems(it) }
    }
}

