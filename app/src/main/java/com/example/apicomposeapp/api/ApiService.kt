package com.example.apicomposeapp.api

import com.example.apicomposeapp.model.Post
import com.example.apicomposeapp.model.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    // Get all posts
    @GET("posts")
    suspend fun getPosts(): List<Post>

    // Get post by ID
    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") id: Int): Post

    // Get all users
    @GET("users")
    suspend fun getUsers(): List<User>

    // Get user by ID
    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: Int): User

    // Example with query parameters
    @GET("posts")
    suspend fun getPostsByUserId(
        @Query("userId") userId: Int
    ): List<Post>
}
