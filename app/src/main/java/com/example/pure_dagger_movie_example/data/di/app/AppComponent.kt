package com.example.pure_dagger_movie_example.data.di.app

import android.app.Application
import com.example.pure_dagger_movie_example.data.di.activity.ActivityComponent
import com.example.pure_dagger_movie_example.data.di.activity.ActivityModule
import com.example.pure_dagger_movie_example.data.di.fragment.FragmentComponent
import com.example.pure_dagger_movie_example.data.di.fragment.FragmentModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface AppComponent {

    fun providesActivitySubComponent(activityModule: ActivityModule): ActivityComponent

    fun providesFragmentSubComponent(): FragmentComponent.Builder

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}