package com.example.movies_application

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.movies_application.Adapter.ListAdapter
import com.example.movies_application.Model.MovieModel

class MovieListActivity : AppCompatActivity() {


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        val listViewMovies: ListView = findViewById(R.id.listViewMovies)
        val movies = intent.getParcelableArrayListExtra<MovieModel>("movies")
        val mutableMovies: MutableList<MovieModel> = movies?.toMutableList() ?: mutableListOf()

        val myListAdapter = ListAdapter(this,mutableMovies)
        listViewMovies.adapter = myListAdapter

        listViewMovies.setOnItemClickListener(){adapterView, view, position, id ->
            val itemAtPos = adapterView.getItemAtPosition(position)
            val itemIdAtPos = adapterView.getItemIdAtPosition(position)
            val imbid= movies?.get(position)?.imdbID
            val intent = Intent(this@MovieListActivity, SingleItemActivity::class.java)
            intent.putExtra("imbid",imbid)
            startActivity(intent)

        }

    }
}