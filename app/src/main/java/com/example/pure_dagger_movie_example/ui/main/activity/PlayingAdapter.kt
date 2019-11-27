package com.example.pure_dagger_movie_example.ui.main.activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.pure_dagger_movie_example.utils.Constants
import com.example.pure_dagger_movie_example.data.entities.Movies
import com.example.pure_dagger_movie_example.R

class PlayingAdapter  (
    private val manager: RequestManager,
    private val movieClicked: MovieClicked
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var moviesList: List<Movies.Result> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return PlayingHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val playingHolder = holder as PlayingHolder
        manager.load(Constants.BASE_IMG_URL + moviesList[position].posterPath)
            .into(playingHolder.img)
        playingHolder.txt.text = moviesList[position].originalTitle
        playingHolder.itemView.setOnClickListener {
            movieClicked.movieClicked(moviesList[position].id)
        }
    }

    class PlayingHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.movie_poster)
        val txt: TextView = view.findViewById(R.id.movie_name)
    }
}