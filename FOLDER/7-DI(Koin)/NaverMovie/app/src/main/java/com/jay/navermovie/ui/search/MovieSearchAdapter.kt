package com.jay.navermovie.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jay.navermovie.R
import com.jay.navermovie.data.search.Movie
import com.jay.navermovie.databinding.ItemMovieBinding

class MovieSearchAdapter(
    private val itemClcik: (Movie) -> Unit
) : RecyclerView.Adapter<MovieSearchAdapter.MovieItemHolder>() {

    lateinit var binding: ItemMovieBinding
    private val items: ArrayList<Movie> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_movie,
            parent,
            false
        )

        return MovieItemHolder(binding).also {
            binding.root.setOnClickListener { position ->
                itemClcik(items[it.adapterPosition])
            }
        }
    }

    override fun onBindViewHolder(holder: MovieItemHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    class MovieItemHolder(private var binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.movieItem = movie
            binding.executePendingBindings()
        }
    }

    fun setItems(items: List<Movie>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clear() {
        this.items.clear()
        notifyDataSetChanged()
    }
}