package com.example.pure_dagger_movie_example

import android.app.Application
import com.example.pure_dagger_movie_example.data.di.app.AppComponent
import com.example.pure_dagger_movie_example.data.di.app.DaggerAppComponent

class MovieApp : Application() {

    lateinit var daggerAppComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        daggerAppComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    fun getAppComponent(): AppComponent {
        return daggerAppComponent
    }
}