package com.example.peliculas

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope

import com.example.peliculas.Adapter.MoviesAdapter
import com.example.peliculas.databinding.ActivityMainBinding
import com.example.peliculas.model.MovieDbClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext



class MainActivity : AppCompatActivity() {
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val moviesAdapter = MoviesAdapter(emptyList())
        {
            Toast.makeText(this@MainActivity, it.title, Toast.LENGTH_SHORT).show()
        }

        binding.recyclerView.adapter = moviesAdapter

        lifecycleScope.launch {
            val apiKey = getString(R.string.api_key)
            val popularMovies = MovieDbClient.service.listPopularMovies(apiKey)
            val body = withContext(Dispatchers.IO) { popularMovies.execute().body() }

                if (body != null) {
                    moviesAdapter.movies = body.results
                    moviesAdapter.notifyDataSetChanged()
                }


        }

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy")
    }
}