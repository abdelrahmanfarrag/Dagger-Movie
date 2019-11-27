package com.example.pure_dagger_movie_example.ui.main.fragment.cast_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import com.example.pure_dagger_movie_example.data.entities.CreditsDetails
import com.example.pure_dagger_movie_example.data.network.DetailsApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CastRepository @Inject constructor(
   private val detailsApi: DetailsApi,
   private val id: String
) {
    private lateinit var details: MediatorLiveData<CreditsDetails>

    fun getMovieDetails(): LiveData<CreditsDetails> {
        details = MediatorLiveData()
        val source: LiveData<CreditsDetails> = LiveDataReactiveStreams.fromPublisher(
            detailsApi.getCreditDetails(id)
                .subscribeOn(Schedulers.io())
        )
        details.addSource(source) { response ->
            details.value = response
            details.removeSource(source)
        }
        return details
    }

}