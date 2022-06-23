package com.example.tracercodetest.repository

import com.example.picturesque.Constants
import com.example.picturesque.FlickrSearchResponse
import com.example.tracercodetest.api.FlickrService
import com.example.tracercodetest.api.GiphyService
import com.example.tracercodetest.models.GiphyResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceRepository {
    suspend fun getGiphy(): Call<GiphyResponse> {

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_GIPHY)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(GiphyService::class.java)

        return service.getRandomGiphy()
    }

    suspend fun getImage(search : String) : FlickrSearchResponse {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_FLICKR)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(FlickrService::class.java)

        return service.fetchImages(search)
    }
}