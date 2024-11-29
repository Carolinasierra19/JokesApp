package com.example.chucknorrisjokesapp.model


data class JokeResponse(
    val id: String,
    val value: String, // El texto del chiste
    val icon_url: String, // La URL del ícono o imagen del chiste
    val categories: List<String>
)
