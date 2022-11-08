package com.example.pixapp.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaApi {
    @GET("api/")
    fun getImages(
        @Query("key")key:String = "31134380-136cd512682ad383b6b7df4b2",
        @Query("q") keyWord: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage : Int = 3
    ): Call<PixaModel>
}