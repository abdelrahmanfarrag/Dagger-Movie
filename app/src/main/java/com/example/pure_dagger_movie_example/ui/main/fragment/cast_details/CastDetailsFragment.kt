package com.example.pure_dagger_movie_example.ui.main.fragment.cast_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.pure_dagger_movie_example.MovieApp
import com.example.pure_dagger_movie_example.R
import com.example.pure_dagger_movie_example.data.di.fragment.FragmentComponent
import com.example.pure_dagger_movie_example.data.di.viewmodel.ViewModelFactoryProvider
import com.example.pure_dagger_movie_example.ui.main.fragment.details.CastClicked
import kotlinx.android.synthetic.main.fragment_credit_details.*
import javax.inject.Inject

class CastDetailsFragment : Fragment(), CastClicked {


    private var id: String? = null

    private lateinit var viewModel: CastViewModel
    @Inject
    lateinit var factory: ViewModelFactoryProvider
    private lateinit var fragmentComponent: FragmentComponent

    companion object {
        fun newInstance(id: String): CastDetailsFragment {
            val castDetailsFragment = CastDetailsFragment()
            val bundle = Bundle()
            bundle.putString("id", id)
            castDetailsFragment.arguments = bundle
            return castDetailsFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_credit_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            id = arguments?.getString("id")
        }
        fragmentComponent = (activity?.application as MovieApp).getAppComponent()
            .providesFragmentSubComponent().injectCastCreditId(id!!).injectCastClicked(this)
            .injectMovieId(0).build()
        fragmentComponent.inject(this)
        viewModel = ViewModelProviders.of(this, factory).get(CastViewModel::class.java)
        viewModel.observeCredits().observe(this, Observer { response ->
            hola_dagger.text = response.toString()
        })

    }

    override fun castId(id: String) {
    }
}