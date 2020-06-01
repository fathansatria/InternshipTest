package com.example.internshiptestfathan.network

import com.example.internshiptestfathan.model.GalleryResponse
import com.example.internshiptestfathan.model.PlaceResponse
import com.example.internshiptestfathan.model.ProfileResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("place.json")
    fun getPlaces(): Call<PlaceResponse>

    @GET("user.json")
    fun getProfile(): Call<ProfileResponse>

    @GET("gallery.json")
    fun getGallery(): Call<GalleryResponse>

}