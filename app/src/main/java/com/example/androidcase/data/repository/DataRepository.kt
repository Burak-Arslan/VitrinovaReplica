package com.example.androidcase.data.repository


import com.example.androidcase.data.local.model.VitrinResponse
import com.example.androidcase.data.service.Api
import javax.inject.Inject
import retrofit2.Response


class DataRepository @Inject constructor(private val apiService: Api) {

    suspend fun getVitrinData(): Response<List<VitrinResponse>> {
        return apiService.getVitrinData()
    }
}
