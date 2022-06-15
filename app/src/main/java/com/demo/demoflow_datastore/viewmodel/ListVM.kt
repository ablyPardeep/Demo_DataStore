package com.demo.demoflow_datastore.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.demoflow_datastore.R
import com.demo.demoflow_datastore.activities.DetailsActivity
import com.demo.demoflow_datastore.adapter.RecyclerAdapter
import com.demo.demoflow_datastore.model.RequestModel
import com.demo.demoflow_datastore.model.RequestModelItem
import com.demo.demoflow_datastore.retrofit.CallApi
import com.demo.demoflow_datastore.retrofit.RequestProcessor
import com.demo.demoflow_datastore.retrofit.RetrofitInterface
import com.demo.demoflow_datastore.util.DataStorePrefs
import com.demo.demoflow_datastore.util.PrefShared

import kotlinx.coroutines.launch
import retrofit2.Response

class ListVM : ViewModel() {

    val getList by lazy { ArrayList<RequestModelItem>() }

    val adapter by lazy { RecyclerAdapter<RequestModelItem>(R.layout.item_view_users_data) }

    private lateinit var bookmarkDataStore: DataStorePrefs

    init {

        adapter.setOnItemClick(object : RecyclerAdapter.OnItemClick {
            override fun onClick(view: View, position: Int, type: String) {
                bookmarkDataStore = DataStorePrefs(view.context)
                when (type) {
                    "itemClick" -> {

                        viewModelScope.launch {

                            bookmarkDataStore.savUserDetail(getList[position])
//                            prefShared.storeUser(
//                                getList[position].origin,
//                                getList[position].name,
//                                getList[position].image.url
//
//                            )
                        }
                        view.context.startActivity(
                            Intent(view.context, DetailsActivity::class.java)
                        )

                    }

                }
            }

        })
    }

    // get  list
    fun eventListApi(context: Context) {
        try {
            CallApi().callService(
                context,
                true,

                object : RequestProcessor<Response<RequestModel>> {
                    override suspend fun sendRequest(retrofitApi: RetrofitInterface): Response<RequestModel> {

                        return retrofitApi.dataList()
                    }

                    override fun onResponse(res: Response<RequestModel>) {
                        if (res.isSuccessful) {
                            val response = res.body()!!
                            getList.clear()
                            getList.addAll(response)

                            adapter.addItems(getList)
                        }
                    }

                    override fun onException(message: String?) {
                        Log.e("userException", "====$message")
                    }
                })

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}