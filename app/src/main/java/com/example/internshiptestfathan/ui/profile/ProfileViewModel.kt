package com.example.internshiptestfathan.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.internshiptestfathan.model.Data
import com.example.internshiptestfathan.model.PlaceResponse
import com.example.internshiptestfathan.model.Profile
import com.example.internshiptestfathan.model.ProfileResponse
import com.example.internshiptestfathan.network.Network
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel : ViewModel() {

    private var allData = MutableLiveData<Profile>()

    internal fun setProfileData(){
        Network.getRetrofit().getProfile().enqueue(object : Callback<ProfileResponse> {

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                Log.d("Network Error", t.stackTrace.toString())
            }

            override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {

                Log.d("Network Succes", response.body().toString())
                val result = (response.body() as ProfileResponse).data
                allData.postValue(result)

            }

        })
    }

    internal fun getProfileData() : LiveData<Profile> {
        return allData
    }
}