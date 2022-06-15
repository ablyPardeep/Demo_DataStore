package com.demo.demoflow_datastore.retrofit

import com.demo.demoflow_datastore.model.RequestModel
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitInterface {

    @GET("breeds")
    suspend fun dataList(): Response<RequestModel>
}