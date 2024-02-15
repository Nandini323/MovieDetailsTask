package com.example.movies_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.movies_application.Network.MovieApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SingleItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_item)

        val imageViewPoster: ImageView = findViewById(R.id.imageViewPoster)
        val textViewTitle: TextView = findViewById(R.id.textViewTitle)
        val textViewYear: TextView = findViewById(R.id.textViewYear)

        val textViewImdbRating: TextView = findViewById(R.id.textViewImdbRating)
        val textViewRottenTomatoesRating: TextView = findViewById(R.id.textViewRottenTomatoesRating)
        val textViewMetacriticRating: TextView = findViewById(R.id.textViewMetacriticRating)
        val textViewReleased: TextView = findViewById(R.id.textViewReleased)
        val textViewRuntime: TextView = findViewById(R.id.textViewRuntime)
        val textViewGenre: TextView = findViewById(R.id.textViewGenre)
        val textViewDirector: TextView = findViewById(R.id.textViewDirector)
        val textViewWriter: TextView = findViewById(R.id.textViewWriter)
        val textViewActors: TextView = findViewById(R.id.textViewActors)
        val textViewPlot: TextView = findViewById(R.id.textViewPlot)
        val textViewLanguage: TextView = findViewById(R.id.textViewLanguage)
        val textViewCountry: TextView = findViewById(R.id.textViewCountry)
        val textViewAwards: TextView = findViewById(R.id.textViewAwards)
        val textViewImdbVotes: TextView = findViewById(R.id.textViewImdbVotes)
        val imbid= intent.extras?.getString("imbid").toString()
        val movieApiService = MovieApiService.RetrofitInstance.movieApiService

        GlobalScope.launch(Dispatchers.IO) {
            try {
//                val movieModel = movieApiService.getMovieDetails()
                val movieModel = movieApiService.getMovieDetails(imbid, "1c441fd8")
                withContext(Dispatchers.Main) {
                    Glide.with(this@SingleItemActivity)
                        .load(movieModel.Poster)
                        .into(imageViewPoster)
                    textViewTitle.text = movieModel.Title
                    textViewYear.text = "Year: ${movieModel.Year}"
                    // Add similar lines for other details yedi nandini

                    textViewImdbRating.text = "IMDb: ${movieModel.Ratings[0].Value}"
                    textViewRottenTomatoesRating.text =
                        "Rotten Tomatoes: ${movieModel.Ratings[1].Value}"
                    textViewReleased.text =
                        "Realeased :"+movieModel.Released
                    textViewRuntime.text =
                        "Runtime :"+movieModel.Runtime
                    textViewGenre.text =
                        "Runtime :"+movieModel.Genre
                    textViewDirector.text =
                        "Runtime :"+movieModel.Runtime
                    textViewWriter.text =
                        "Runtime :"+movieModel.Writer
                    textViewActors.text =
                        "Runtime :"+movieModel.Actors
                    textViewPlot.text =
                        "Runtime :"+movieModel.Plot

                    Log.i(
                        "TAG",
                        "successful response. Movie Title: ${movieModel.Title}, Year: ${movieModel.Year}"
                    )
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@SingleItemActivity, "Error: ${e.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}
