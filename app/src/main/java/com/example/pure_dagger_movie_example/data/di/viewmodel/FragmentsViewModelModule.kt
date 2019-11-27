package com.example.pure_dagger_movie_example.data.di.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pure_dagger_movie_example.data.di.scopes.ViewModelKey
import com.example.pure_dagger_movie_example.ui.main.fragment.cast_details.CastViewModel
import com.example.pure_dagger_movie_example.ui.main.fragment.details.DetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FragmentsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    abstract fun bindsMainViewModel(mainViewModel: DetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CastViewModel::class)
    abstract fun bindsCastViewModel(castViewModel: CastViewModel): ViewModel

}