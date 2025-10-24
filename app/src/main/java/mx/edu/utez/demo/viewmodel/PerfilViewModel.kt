package mx.edu.utez.demo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PerfilViewModel : ViewModel() {

    private val _expandedMenuIndex = MutableStateFlow<Int?>(null)
    val expandedMenuIndex: StateFlow<Int?> = _expandedMenuIndex

    private val _profileData = MutableStateFlow(ProfileData())
    val profileData: StateFlow<ProfileData> = _profileData

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts

    data class ProfileData(
        val username: String = "Nombre de usuario",
        val followers: Int = 100,
        val following: Int = 120,
        val description: String = "Descripción del perfil",
        val isFollowing: Boolean = false
    )

    data class Post(
        val id: Int,
        val imageRes: Int? = null
    )

    fun onPostOptionsClick(index: Int) {
        _expandedMenuIndex.value = index
    }

    fun onDismissMenu() {
        _expandedMenuIndex.value = null
    }

    fun toggleFollow() {
        val currentData = _profileData.value
        _profileData.value = currentData.copy(
            isFollowing = !currentData.isFollowing,
            followers = if (!currentData.isFollowing) currentData.followers + 1 else currentData.followers - 1
        )
    }

    fun loadProfileData() {
        _isLoading.value = true

        viewModelScope.launch {
            try {
                delay(1500)

                _profileData.value = ProfileData(
                    username = "Ana García",
                    followers = 156,
                    following = 89,
                    description = "Apasionada por la fotografía y los viajes ️",
                    isFollowing = false
                )

                _posts.value = List(9) { index ->
                    Post(id = index)
                }

            } catch (e: Exception) {
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun editPost(postId: Int) {
        viewModelScope.launch {
            onDismissMenu()
        }
    }

    fun viewPostStats(postId: Int) {
        viewModelScope.launch {
            onDismissMenu()
        }
    }

    fun deletePost(postId: Int) {
        _isLoading.value = true

        viewModelScope.launch {
            try {
                delay(1000)

                val currentPosts = _posts.value.toMutableList()
                currentPosts.removeAll { it.id == postId }
                _posts.value = currentPosts

            } catch (e: Exception) {
            } finally {
                _isLoading.value = false
                onDismissMenu()
            }
        }
    }

    fun updateProfile(newUsername: String? = null, newDescription: String? = null) {
        val currentData = _profileData.value
        _profileData.value = currentData.copy(
            username = newUsername ?: currentData.username,
            description = newDescription ?: currentData.description
        )
    }
}