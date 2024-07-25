package com.heartcode.machinetestapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AlbumViewModel : ViewModel() {
    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>>
        get() = _albums
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    init {
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.getAlbums()
                _albums.value = response
            } catch (e: Exception) {
                _errorMessage.value = "Error fetching albums: ${e.message}"
            }
        }
    }
}
