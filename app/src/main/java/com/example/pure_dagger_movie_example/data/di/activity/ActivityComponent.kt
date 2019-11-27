package com.example.pure_dagger_movie_example.data.di.activity

import com.example.pure_dagger_movie_example.data.di.viewmodel.ActivityViewModelModule
import com.example.pure_dagger_movie_example.data.di.scopes.PerActivity
import com.example.pure_dagger_movie_example.ui.main.activity.MainActivity
import com.example.pure_dagger_movie_example.data.di.viewmodel.ViewModelFactoryModule
import dagger.Subcomponent


@PerActivity
@Subcomponent(modules = [ActivityModule::class, ViewModelFactoryModule::class, ActivityViewModelModule::class])
interface ActivityComponent {

    fun injectMain(mainActivity: MainActivity)
/*
    @Subcomponent.Builder
    interface Builder {

        @BindsInstance
        fun bindActivity(mainActivity: MainActivity): Builder

        fun build(): ActivityComponent
    }
*/
}