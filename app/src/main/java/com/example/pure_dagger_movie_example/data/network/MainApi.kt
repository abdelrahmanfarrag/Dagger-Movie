package com.example.pure_dagger_movie_example.data.network

import com.example.pure_dagger_movie_example.data.entities.Details
import com.example.pure_dagger_movie_example.data.entities.Movies
import com.example.pure_dagger_movie_example.utils.Constants
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface MainApi {

    @GET(Constants.NOW_PLAYING)
    fun getPlayingMovies(): Flowable<Movies>

}