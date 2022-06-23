package com.example.tracercodetest.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.picturesque.FlickrSearchResponse
import com.example.tracercodetest.models.GiphyResponse
import com.example.tracercodetest.repository.ServiceRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

const val TAG = "HomeViewModel_getGiphy"
class HomeViewModel(private val service : ServiceRepository) : ViewModel() {

    private val _response = MutableLiveData<GiphyResponse>()
    val response : LiveData<GiphyResponse> get() = _response

    private val _imageResponse = MutableLiveData<FlickrSearchResponse>()
    val imageResponse : LiveData<FlickrSearchResponse> get() = _imageResponse

    private val _loading = MutableLiveData(false)
    val loading : LiveData<Boolean> get() = _loading


    fun getGiphy() {
        _loading.postValue(true)
        viewModelScope.launch {
            try {

                service.getGiphy().enqueue(object : Callback<GiphyResponse> {
                    override fun onResponse(
                        call: Call<GiphyResponse>,
                        response: Response<GiphyResponse>
                    ) {
                        _response.postValue(response.body())
                        _loading.postValue(false)
                    }

                    override fun onFailure(call: Call<GiphyResponse>, t: Throwable) {
                        Log.d(TAG, "onFailure: request failed")
                        _loading.postValue(true)
                    }

                })
            } catch (e: Exception) {

            }
        }
    }

    fun getImage(search : String) {
        _loading.postValue(true)
        viewModelScope.launch {
            val imageResponse = service.getImage(search)
            _imageResponse.postValue(imageResponse)
            _loading.postValue(false)
        }
    }
}