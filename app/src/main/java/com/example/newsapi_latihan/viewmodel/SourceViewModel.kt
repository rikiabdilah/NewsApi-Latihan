package com.example.newsapi_latihan.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapi_latihan.model.ResponseDataSource
import com.example.newsapi_latihan.model.Source
import com.example.newsapi_latihan.network.ApiService
import com.example.newsapi_latihan.network.NetworkClient
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SourceViewModel @Inject constructor(var api : ApiService) : ViewModel() {

    lateinit var liveDataSource : MutableLiveData<List<Source>?>

    init {
        liveDataSource = MutableLiveData()
    }

    fun getDataSource(): MutableLiveData<List<Source>?> {
        return  liveDataSource
    }

    fun callApiSource(category : String){
        api.getAllSources(category)
            .enqueue(object : Callback<ResponseDataSource> {
                override fun onResponse(
                    call: Call<ResponseDataSource>,
                    response: Response<ResponseDataSource>
                ) {
                    if (response.isSuccessful){
                        liveDataSource.postValue(response.body()!!.sources)
                        Log.d(ContentValues.TAG, "onResponse: ${response.body()!!.sources.size}")

                    }else{
                        Log.d(ContentValues.TAG, "onResponse: Unsuccessfully")
                        liveDataSource.postValue(null)                    }
                }

                override fun onFailure(call: Call<ResponseDataSource>, t: Throwable) {
                    Log.d(ContentValues.TAG, "onFailure: ${t.message}")
                    liveDataSource.postValue(null)}
            })
    }
}