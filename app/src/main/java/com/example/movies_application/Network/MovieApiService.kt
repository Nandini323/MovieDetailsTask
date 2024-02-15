package com.example.movies_application.Network

import com.example.movies_application.Model.MoviePageDetails
import com.example.movies_application.Model.MovieSearchResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    object RetrofitInstance {

        private const val BASE_URL = "http://www.omdbapi.com"
//        http://www.omdbapi.com/?i=tt5475174&apikey=1c441fd8

        val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val movieApiService: MovieApiService by lazy {
            retrofit.create(MovieApiService::class.java)
        }
    }

    @GET("/?i=tt3896198&apikey=1c441fd8")
    suspend fun getMovieDetails(): MoviePageDetails

    @GET("/")
    suspend fun searchMovies(@Query("s") query: String, @Query("apikey") apiKey: String): MovieSearchResponse


    @GET("/")
    suspend fun getMovieDetails(@Query("i") imdbId: String, @Query("apikey") apiKey: String): MoviePageDetails

//    @GET("/")
//    suspend fun getMovieDetail(@Query("i") query: String, @Query("apikey") apiKey: String): MoviePageDetails

}