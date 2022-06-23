package com.example.tracercodetest.api

import com.example.picturesque.Constants.API_KEY_FLICKR
import com.example.picturesque.FlickrSearchResponse
import retrofit2.http.*

interface FlickrService {
    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&api_key=$API_KEY_FLICKR&per_page=20")
    suspend fun fetchImages(@Query("text") searchString: String,
                            @Query("page") page : String = "1"): FlickrSearchResponse

}