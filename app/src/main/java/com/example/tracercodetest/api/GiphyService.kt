package com.example.tracercodetest.api

import com.example.picturesque.Constants
import com.example.tracercodetest.models.GiphyResponse
import retrofit2.Call
import retrofit2.http.GET

interface GiphyService {
    @GET("random/?api_key=${Constants.API_KEY_GIPHY}&tag=&rating=g")
    suspend fun getRandomGiphy() : Call<GiphyResponse>
}