package com.example.internshiptestfathan.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.internshiptestfathan.model.Data
import com.example.internshiptestfathan.model.PlaceResponse
import com.example.internshiptestfathan.network.Network
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private var allData = MutableLiveData<Data>()

    internal fun setData(){
        Network.getRetrofit().getPlaces().enqueue(object : Callback<PlaceResponse>{

            override fun onFailure(call: Call<PlaceResponse>, t: Throwable) {
                Log.d("Network Error", t.stackTrace.toString())
            }

            override fun onResponse(call: Call<PlaceResponse>, response: Response<PlaceResponse>) {

                Log.d("Network Succes", response.body().toString())
                val result = (response.body() as PlaceResponse).data
                allData.postValue(result)

            }

        })
    }

    internal fun getAllData() : LiveData<Data>{
        return allData
    }

}