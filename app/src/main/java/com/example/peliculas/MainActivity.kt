package com.example.peliculas

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.widget.Toast
import androidx.lifecycle.lifecycleScope

import com.example.peliculas.Adapter.MoviesAdapter
import com.example.peliculas.databinding.ActivityMainBinding
import com.example.peliculas.model.MovieDb
import com.example.peliculas.model.MovieDbClient

import kotlinx.coroutines.launch




class MainActivity : AppCompatActivity() {
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val moviesAdapter = MoviesAdapter(emptyList()) {navigateTo(it)}

        binding.recyclerView.adapter = moviesAdapter

        lifecycleScope.launch {
            val apiKey = getString(R.string.api_key)
            val popularMovies = MovieDbClient.service.listPopularMovies(apiKey)
            moviesAdapter.movies = popularMovies.results
            moviesAdapter.notifyDataSetChanged()



        }

    }

    private fun navigateTo(it: MovieDb) {
        val intent = Intent(this,DetailActivity::class.java)
        startActivity(intent)
    }


}