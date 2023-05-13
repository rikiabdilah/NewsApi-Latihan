package com.example.newsapi_latihan.network

import com.example.newsapi_latihan.model.ResponseDataSource
import com.example.newsapi_latihan.model.Source
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines/sources")
    fun getAllSources(
        @Query("category") category:String,
        @Query("apikey") apikey:String = "2f08826977e548b2aebda1f74c4d7d92"
    ): Call<ResponseDataSource>
}