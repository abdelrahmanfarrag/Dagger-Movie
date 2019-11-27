package com.example.pure_dagger_movie_example.data.di.fragment

import com.example.pure_dagger_movie_example.data.di.scopes.PerFragment
import com.example.pure_dagger_movie_example.data.di.viewmodel.FragmentsViewModelModule
import com.example.pure_dagger_movie_example.data.di.viewmodel.ViewModelFactoryModule
import com.example.pure_dagger_movie_example.ui.main.fragment.cast_details.CastDetailsFragment
import com.example.pure_dagger_movie_example.ui.main.fragment.details.CastClicked
import com.example.pure_dagger_movie_example.ui.main.fragment.details.MovieDetailsFragment
import dagger.Binds
import dagger.BindsInstance
import dagger.Subcomponent

@PerFragment
@Subcomponent(modules = [FragmentModule::class, ViewModelFactoryModule::class, FragmentsViewModelModule::class])
interface FragmentComponent {

    fun inject(detailsFragment: MovieDetailsFragment)

    fun inject(castDetailsFragment: CastDetailsFragment)

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun injectMovieId(id: Int): Builder

        @BindsInstance
        fun injectCastClicked(castClicked: CastClicked): Builder

        @BindsInstance
        fun injectCastCreditId(id: String): Builder

        fun build(): FragmentComponent
    }
}