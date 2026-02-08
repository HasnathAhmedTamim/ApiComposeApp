package com.example.apicomposeapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apicomposeapp.model.Post
import com.example.apicomposeapp.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {

    private val repository = PostRepository()

    // State for posts
    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts.asStateFlow()

    // State for loading
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    // State for error
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    // State for selected post
    private val _selectedPost = MutableStateFlow<Post?>(null)
    val selectedPost: StateFlow<Post?> = _selectedPost.asStateFlow()

    // Initialize: Load posts when ViewModel is created
    init {
        loadPosts()
    }

    // Load all posts
    fun loadPosts() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                _posts.value = repository.getPosts()
            } catch (e: Exception) {
                _error.value = "Failed to load posts: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Load post by ID
    fun loadPostById(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _selectedPost.value = repository.getPostById(id)
            } catch (e: Exception) {
                _error.value = "Failed to load post: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Clear error
    fun clearError() {
        _error.value = null
    }

    // Refresh data
    fun refresh() {
        loadPosts()
    }
}