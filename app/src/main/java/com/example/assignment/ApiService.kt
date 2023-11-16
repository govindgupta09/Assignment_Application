package com.example.assignment
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("v1/cryptocurrency/listings/latest")
    fun fetchData(): Call<DataList>
}