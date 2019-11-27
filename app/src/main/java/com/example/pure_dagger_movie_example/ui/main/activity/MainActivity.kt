package com.example.pure_dagger_movie_example.ui.main.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.pure_dagger_movie_example.MovieApp
import com.example.pure_dagger_movie_example.R
import com.example.pure_dagger_movie_example.data.di.activity.ActivityComponent
import com.example.pure_dagger_movie_example.data.di.activity.ActivityModule
import com.example.pure_dagger_movie_example.data.di.viewmodel.ViewModelFactoryProvider
import com.example.pure_dagger_movie_example.ui.main.fragment.details.MovieDetailsFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(),
    MovieClicked {

    private lateinit var mainViewModel: MainViewModel
    @Inject
    lateinit var factory: ViewModelFactoryProvider
    @Inject
    lateinit var playingAdapter: PlayingAdapter
    private lateinit var activityComponent: ActivityComponent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityComponent = (application as MovieApp).getAppComponent()
            .providesActivitySubComponent(ActivityModule(this))
        activityComponent.injectMain(this)
        mainViewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        mainViewModel.observePlayingMovies().observe(this, Observer { providedData ->
            playingAdapter.moviesList = providedData.results
            playing_movies.adapter = playingAdapter

        })
    }

    override fun movieClicked(id: Int) {
        @Suppress("JAVA_CLASS_ON_COMPANION")
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, MovieDetailsFragment.newInstance(id))
            .addToBackStack(MovieDetailsFragment.javaClass.simpleName)
            .commit()
    }
}
