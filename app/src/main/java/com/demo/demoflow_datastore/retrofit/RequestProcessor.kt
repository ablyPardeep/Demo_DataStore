package com.demo.demoflow_datastore.retrofit

interface RequestProcessor<T> {

    suspend fun sendRequest(retrofitApi: RetrofitInterface): T

    fun onResponse(res: T)

    fun onException(message: String?)

}