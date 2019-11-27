package com.example.pure_dagger_movie_example.data.di.activity

import com.bumptech.glide.RequestManager
import com.example.pure_dagger_movie_example.data.di.scopes.PerActivity
import com.example.pure_dagger_movie_example.data.di.scopes.PerFragment
import com.example.pure_dagger_movie_example.data.network.DetailsApi
import com.example.pure_dagger_movie_example.data.network.MainApi
import com.example.pure_dagger_movie_example.ui.main.activity.MainActivity
import com.example.pure_dagger_movie_example.ui.main.activity.MainRepository
import com.example.pure_dagger_movie_example.ui.main.activity.MovieClicked
import com.example.pure_dagger_movie_example.ui.main.activity.PlayingAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ActivityModule constructor(private val movieClicked: MovieClicked) {

    @Provides
    @PerActivity
    fun providesMainActivity():MovieClicked {
        return movieClicked
    }

    @Provides
    @PerActivity
    fun providesMainApi(retrofit: Retrofit): MainApi {
        return retrofit.create(MainApi::class.java)
    }

    @Provides
    @PerActivity
    fun providesPlayingAdapter(
        requestManager: RequestManager,
        movieClicked: MovieClicked
    ): PlayingAdapter {
        return PlayingAdapter(
            requestManager,
            movieClicked
        )
    }

    @Provides
    @PerActivity
    fun providesRepository(mainApi: MainApi): MainRepository {
        return MainRepository(mainApi)
    }

}