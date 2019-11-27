package com.example.pure_dagger_movie_example.ui.main.fragment.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.pure_dagger_movie_example.data.entities.Credits
import com.example.pure_dagger_movie_example.data.entities.Details
import com.example.pure_dagger_movie_example.data.entities.Movies
import javax.inject.Inject

class DetailsViewModel @Inject constructor(private val repository: DetailsRepository) :
    ViewModel() {

    fun observeMovieDetails(): LiveData<out Details> {
        return repository.getMovieDetails()
    }

    @Suppress("unused")
    fun observePopularMovies(): LiveData<out Movies> {
        return repository.getPopularMovies()
    }

    fun observeCastMovies(): LiveData<out Credits> {
        return repository.getMovieCast()
    }
}