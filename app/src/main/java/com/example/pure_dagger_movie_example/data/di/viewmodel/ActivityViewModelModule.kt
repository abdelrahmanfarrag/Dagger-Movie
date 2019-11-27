package com.example.pure_dagger_movie_example.data.di.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pure_dagger_movie_example.data.di.scopes.ViewModelKey
import com.example.pure_dagger_movie_example.ui.main.activity.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ActivityViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindsMainViewModel(mainViewModel: MainViewModel): ViewModel

}