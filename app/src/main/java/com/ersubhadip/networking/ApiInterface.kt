package com.ersubhadip.networking

import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("/todos")
    suspend fun getApi():Response<List<ApiDataModel>>
}