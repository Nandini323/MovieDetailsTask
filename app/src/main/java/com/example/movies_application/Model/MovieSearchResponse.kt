package com.example.movies_application.Model

data class MovieSearchResponse(
    val Search: List<MovieModel>,
    val totalResults: String,
    val Response: String
)