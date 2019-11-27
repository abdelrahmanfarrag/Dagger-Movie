package com.example.pure_dagger_movie_example.data.di.activity

import com.example.pure_dagger_movie_example.ui.main.activity.MainActivity
import com.example.pure_dagger_movie_example.ui.main.activity.MovieClicked
import dagger.Binds
import dagger.Module

@Module
abstract class MovieClickModule {
    @Binds
    abstract fun bindsMovieClick(mainActivity: MainActivity): MovieClicked
}