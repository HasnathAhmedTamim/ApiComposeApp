package com.example.apicomposeapp.repository

import com.example.apicomposeapp.api.RetrofitClient
import com.example.apicomposeapp.model.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
//import javax.inject.Inject

class PostRepository {

    private val apiService = RetrofitClient.apiService

    // Simple suspend function
    suspend fun getPosts(): List<Post> {
        return apiService.getPosts()
    }

    // Using Flow for reactive programming
    fun getPostsFlow(): Flow<List<Post>> = flow {
        val posts = apiService.getPosts()
        emit(posts)  // Emit the data
    }

    suspend fun getPostById(id: Int): Post {
        return apiService.getPostById(id)
    }

    suspend fun getPostsByUserId(userId: Int): List<Post> {
        return apiService.getPostsByUserId(userId)
    }
}