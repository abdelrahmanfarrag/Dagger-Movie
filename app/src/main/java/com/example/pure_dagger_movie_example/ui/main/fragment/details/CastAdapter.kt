package com.example.pure_dagger_movie_example.ui.main.fragment.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.pure_dagger_movie_example.R
import com.example.pure_dagger_movie_example.data.entities.Credits
import com.example.pure_dagger_movie_example.utils.Constants
import javax.inject.Inject

class CastAdapter @Inject constructor(
    private val glide: RequestManager,
    private val item: CastClicked
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var castMember: List<Credits.Cast> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cast, parent, false)
        return CastHolder(view)
    }

    override fun getItemCount(): Int {
        return castMember.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val castHolder = holder as CastHolder
        glide.load(Constants.BASE_IMG_URL + castMember[position].profilePath)
            .into(castHolder.profile)
        castHolder.data.text = castMember[position].name + "\n" + castMember[position].character
        castHolder.itemView.setOnClickListener {
            item.castId(castMember[position].creditId)
        }

    }

    class CastHolder(view: View) : RecyclerView.ViewHolder(view) {
        val profile: ImageView = view.findViewById(R.id.cast_profile)
        val data: TextView = view.findViewById(R.id.cast_data)
    }
}