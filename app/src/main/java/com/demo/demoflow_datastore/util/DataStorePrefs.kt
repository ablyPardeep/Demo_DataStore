package com.demo.demoflow_datastore.util

import android.content.Context
import androidx.datastore.CorruptionException
import androidx.datastore.DataStore
import androidx.datastore.Serializer
import androidx.datastore.createDataStore
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import com.demo.demoflow_datastore.UserPreferences
import com.demo.demoflow_datastore.model.RequestModelItem
import kotlinx.coroutines.flow.map
import java.io.InputStream
import java.io.OutputStream

class DataStorePrefs(context: Context) {
    private val applicationContext = context.applicationContext
     val dataStore: DataStore<UserPreferences>

    init {
        dataStore = applicationContext.createDataStore(
            fileName = "bookmark.pb",
            serializer = UserDetailSerializer
        )
    }


    suspend fun savUserDetail(userDetail: RequestModelItem) {
        dataStore.updateData { currentuserDetail->
            currentuserDetail.toBuilder()
                .setCountry(userDetail.origin)
                .setName(userDetail.name)
                .setPicture(userDetail.image.url)
                .build()
        }
    }

    object UserDetailSerializer : Serializer<UserPreferences> {
        override fun readFrom(input: InputStream): UserPreferences {
            try {
                return UserPreferences.parseFrom(input)
            } catch (exception: InvalidProtocolBufferException) {
                throw CorruptionException("Cannot read proto.", exception)
            }
        }

        override fun writeTo(
            t: UserPreferences,
            output: OutputStream
        ) = t.writeTo(output)
    }
}