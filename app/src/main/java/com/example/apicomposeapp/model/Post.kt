package com.example.apicomposeapp.model

// This data model for posts
data class Post(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)