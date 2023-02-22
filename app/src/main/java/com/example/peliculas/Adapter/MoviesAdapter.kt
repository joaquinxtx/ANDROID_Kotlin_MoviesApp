package com.example.peliculas.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.peliculas.databinding.ViewMovieItemBinding
import com.example.peliculas.model.MovieDb


class MoviesAdapter(
    var movies : List<MovieDb>,
    private val movieClickedListener:(MovieDb)->Unit
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding =
            ViewMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)


        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener{movieClickedListener(movie)}
    }

    override fun getItemCount() = movies.size


    class ViewHolder(private val binding: ViewMovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieDb) {

            Glide
                .with(binding.root.context)
                .load("https://image.tmdb.org/t/p/w185/${movie.poster_path}")
                .into(binding.image)

        }
    }

}