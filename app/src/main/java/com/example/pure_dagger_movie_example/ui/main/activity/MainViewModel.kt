package com.example.pure_dagger_movie_example.ui.main.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.pure_dagger_movie_example.data.entities.Movies
import javax.inject.Inject

class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    fun observePlayingMovies(): LiveData<out Movies> {
        return mainRepository.getPlayingMovies()
    }
}