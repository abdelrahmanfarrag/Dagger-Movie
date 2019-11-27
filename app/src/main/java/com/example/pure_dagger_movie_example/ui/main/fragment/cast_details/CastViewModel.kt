package com.example.pure_dagger_movie_example.ui.main.fragment.cast_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.pure_dagger_movie_example.data.entities.CreditsDetails
import javax.inject.Inject

class CastViewModel @Inject constructor(private val repository: CastRepository) : ViewModel() {

    fun observeCredits(): LiveData<out CreditsDetails> {
        return repository.getMovieDetails()
    }
}