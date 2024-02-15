package com.example.movies_application.Adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.movies_application.Model.MovieModel
import com.example.movies_application.R

class ListAdapter(private val context: Activity, private val movie: MutableList<MovieModel>): ArrayAdapter<MovieModel>(context, R.layout.list_item_movie, movie){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.list_item_movie, null, true)

        val titleText = rowView.findViewById(R.id.textViewTitle) as TextView
        val imageView = rowView.findViewById(R.id.imageViewPoster) as ImageView
        val year = rowView.findViewById(R.id.textViewYear) as TextView
        val textViewType = rowView.findViewById(R.id.textViewType) as TextView

        titleText.text = movie[position].Title
        val imagelink = movie[position].Poster
        Glide.with(context)
            .load(imagelink)
            .into(imageView)
        textViewType.text = movie[position].imdbID
        year.text = movie[position].Year

        return rowView
    }
}