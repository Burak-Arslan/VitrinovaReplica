package com.example.androidcase.data.service


import com.example.androidcase.data.local.model.VitrinResponse
import retrofit2.Response
import retrofit2.http.GET


interface Api {
    @GET("api/v2/discover")
    suspend fun getVitrinData(): Response<List<VitrinResponse>>
}