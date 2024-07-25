package com.heartcode.machinetestapp

import retrofit2.http.GET

interface ApiService {
    @GET("albums")
    suspend fun getAlbums(): List<Album>
}
