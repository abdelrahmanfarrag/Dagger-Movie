package com.example.pure_dagger_movie_example.ui.main.fragment.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import com.example.pure_dagger_movie_example.data.entities.Credits
import com.example.pure_dagger_movie_example.data.entities.Details
import com.example.pure_dagger_movie_example.data.entities.Movies
import com.example.pure_dagger_movie_example.data.network.DetailsApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailsRepository @Inject constructor(private val api: DetailsApi, private val id: Int) {
    private lateinit var data: MediatorLiveData<Details>
    private lateinit var popular: MediatorLiveData<Movies>
    private lateinit var cast: MediatorLiveData<Credits>

    fun getMovieDetails(): LiveData<Details> {
        data = MediatorLiveData()
        val source: LiveData<Details> = LiveDataReactiveStreams.fromPublisher(
            api.getDetails(id)
                .subscribeOn(Schedulers.io())
        )
        data.addSource(source) { response ->
            data.value = response
            data.removeSource(source)
        }
        return data
    }

    fun getMovieCast(): LiveData<Credits> {
        cast = MediatorLiveData()
        val source: LiveData<Credits> = LiveDataReactiveStreams.fromPublisher(
            api.getMovieCredits(id)
                .subscribeOn(Schedulers.io())
        )
        cast.addSource(source) { response ->
            cast.value = response
            cast.removeSource(source)
        }
        return cast

    }

    fun getPopularMovies(): LiveData<Movies> {
        popular = MediatorLiveData()
        val source: LiveData<Movies> = LiveDataReactiveStreams.fromPublisher(
            api.getPopularMovies()
                .subscribeOn(Schedulers.io())
        )
        popular.addSource(source) { response ->
            popular.value = response
            popular.removeSource(source)
        }
        return popular
    }
}