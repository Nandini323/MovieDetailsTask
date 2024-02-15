package com.example.movies_application


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.Surface
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

import androidx.appcompat.widget.Toolbar

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.example.movies_application.Adapter.ListAdapter
import com.example.movies_application.Model.MovieModel
import com.example.movies_application.Model.Movies
import com.example.movies_application.Network.MovieApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity  : AppCompatActivity() {

    private lateinit var toolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        searchMovies("Gard")

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val search = menu.findItem(R.id.appSearchBar)
        val searchView = search.actionView as SearchView
        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    searchMovies(query)
                }
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    searchMovies(newText)
                }
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }


    private fun searchMovies(query: String) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = withContext(Dispatchers.IO) {
                    MovieApiService.RetrofitInstance.movieApiService.searchMovies(query, "1c441fd8")
                }
                if (response.Response == "True") {
                    val movies = response.Search

                    val listViewMovies: ListView = findViewById(R.id.listViewMovies)
                    //val movies = intent.getParcelableArrayListExtra<MovieModel>("movies")
                    val mutableMovies: MutableList<MovieModel> = movies?.toMutableList() ?: mutableListOf()

                    val myListAdapter = ListAdapter(this@MainActivity,mutableMovies)
                    listViewMovies.adapter = myListAdapter

                    listViewMovies.setOnItemClickListener(){adapterView, view, position, id ->
                        val itemAtPos = adapterView.getItemAtPosition(position)
                        val itemIdAtPos = adapterView.getItemIdAtPosition(position)
                        val imbid= movies?.get(position)?.imdbID
                        val intent = Intent(this@MainActivity, SingleItemActivity::class.java)
                        intent.putExtra("imbid",imbid)
                        startActivity(intent)

                    }

                } else {
//                     Log.e("API Error", "Response: ${response.Error}")
                }
            } catch (e: Exception) {
                Log.e("Network Error", "Error: ${e.message}", e)
            }
        }
    }

}
