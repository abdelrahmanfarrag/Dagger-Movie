package com.example.pure_dagger_movie_example.ui.main.fragment.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestManager
import com.example.pure_dagger_movie_example.MovieApp
import com.example.pure_dagger_movie_example.R
import com.example.pure_dagger_movie_example.data.di.fragment.FragmentComponent
import com.example.pure_dagger_movie_example.data.di.viewmodel.ViewModelFactoryProvider
import com.example.pure_dagger_movie_example.ui.main.fragment.cast_details.CastDetailsFragment
import com.example.pure_dagger_movie_example.utils.Constants
import kotlinx.android.synthetic.main.fragment_details.*
import javax.inject.Inject

class MovieDetailsFragment : Fragment(), CastClicked {


    private var id: Int? = null
    private lateinit var detailsViewModel: DetailsViewModel
    private lateinit var fragmentComponent: FragmentComponent
    @Inject
    lateinit var factory: ViewModelFactoryProvider
    @Inject
    lateinit var requestManager: RequestManager
    @Inject
    lateinit var castAdapter: CastAdapter

    companion object {
        fun newInstance(id: Int): MovieDetailsFragment {
            val movieDetailsFragment =
                MovieDetailsFragment()
            val movieBundle = Bundle()
            movieBundle.putInt("id", id)
            movieDetailsFragment.arguments = movieBundle
            return movieDetailsFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)

    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            id = arguments?.getInt("id")
        }
        fragmentComponent =
            (activity?.application as MovieApp).getAppComponent().providesFragmentSubComponent()
                .injectMovieId(id!!).injectCastClicked(this).injectCastCreditId("").build()
        fragmentComponent.inject(this)
        detailsViewModel = ViewModelProviders.of(this, factory).get(DetailsViewModel::class.java)
        detailsViewModel.observeMovieDetails().observe(this, Observer { details ->
            movie_rating.text = "${details.voteAverage}/10"
            movie_additional_data.text = details.originalTitle + "\n" + details.releaseDate
            movie_detail_overview.text = details.overview
            requestManager.load(Constants.BASE_IMG_URL + details.posterPath)
                .into(movie_detail_poster)
        })
        detailsViewModel.observeCastMovies().observe(this, Observer { credits ->
            castAdapter.castMember = credits.cast
            movie_cast.adapter = castAdapter
        })
    }

    override fun castId(id: String) {
        val fragment = CastDetailsFragment.newInstance(id)
        val transaction = fragmentManager?.beginTransaction()
        transaction?.add(R.id.fragment_container, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }
}