package com.example.pure_dagger_movie_example.data.network

import com.example.pure_dagger_movie_example.data.entities.Credits
import com.example.pure_dagger_movie_example.data.entities.CreditsDetails
import com.example.pure_dagger_movie_example.data.entities.Details
import com.example.pure_dagger_movie_example.data.entities.Movies
import com.example.pure_dagger_movie_example.utils.Constants
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsApi {

    @GET(Constants.DETAILS)
    fun getDetails(@Path("movie_id") id: Int): Flowable<Details>

    @GET(Constants.CREDITS)
    fun getMovieCredits(@Path("movie_id") id: Int): Flowable<Credits>

    @GET(Constants.CREDIT_DETAILS)
    fun getCreditDetails(@Path("credit_id") id: String): Flowable<CreditsDetails>


    @GET(Constants.POPULAR)
    fun getPopularMovies(): Flowable<Movies>


}