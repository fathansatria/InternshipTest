package com.example.internshiptestfathan.ui.gallery

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.internshiptestfathan.model.Data
import com.example.internshiptestfathan.model.Gallery
import com.example.internshiptestfathan.model.GalleryResponse
import com.example.internshiptestfathan.model.PlaceResponse
import com.example.internshiptestfathan.network.Network
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GalleryViewModel : ViewModel() {

    private var allData = MutableLiveData<List<Gallery>>()

    internal fun setGalleryData(){
        Network.getRetrofit().getGallery().enqueue(object : Callback<GalleryResponse> {

            override fun onFailure(call: Call<GalleryResponse>, t: Throwable) {
                Log.d("Network Error", t.stackTrace.toString())
            }

            override fun onResponse(call: Call<GalleryResponse>, response: Response<GalleryResponse>) {

                Log.d("Network Succes", response.body().toString())
                val result = (response.body() as GalleryResponse).data
                allData.postValue(result)

            }

        })
    }

    internal fun getAllGalleryData() : LiveData<List<Gallery>> {
        return allData
    }

}