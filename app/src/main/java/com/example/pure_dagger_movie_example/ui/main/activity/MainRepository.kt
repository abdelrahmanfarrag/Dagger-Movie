package com.example.pure_dagger_movie_example.ui.main.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import com.example.pure_dagger_movie_example.data.network.MainApi
import com.example.pure_dagger_movie_example.data.entities.Movies
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainRepository @Inject constructor(private val mainApi: MainApi) {
    private lateinit var data: MediatorLiveData<Movies>

    fun getPlayingMovies(): LiveData<Movies> {
        data = MediatorLiveData()
        val source: LiveData<Movies> = LiveDataReactiveStreams.fromPublisher(
            mainApi.getPlayingMovies()
                .subscribeOn(Schedulers.io())
        )
        data.addSource(source) { response ->
            data.value = response
            data.removeSource(source)
        }
        return data
    }


}