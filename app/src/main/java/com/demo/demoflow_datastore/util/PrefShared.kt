package com.demo.demoflow_datastore.util

import android.content.Context
import androidx.datastore.createDataStore
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import com.demo.demoflow_datastore.model.RequestModelItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PrefShared (context: Context) {
    // create the data store and give it a name
     val dataStore = context.createDataStore(name = "preferences")

    //  create some keys and that will used for get the data
    companion object {
        val country = preferencesKey<String>("country")
        val name = preferencesKey<String>("name")
        val picture = preferencesKey<String>("picture")
    }

    suspend fun storeUser(country1: String, name1: String, pic: String) {
        dataStore.edit {
            it[country] = country1
            it[name] = name1
            it[picture] = pic


        }
    }

    // Create a country flow to retrieve name from the preferences
    val countryFlow: Flow<String> = dataStore.data.map {
        it[country] ?: ""
    }


    // Create a name flow to retrieve name from the preferences
    val nameFlow: Flow<String> = dataStore.data.map {
        it[name] ?: ""
    }

    val pic : Flow<String> = dataStore.data.map {
        it[picture] ?: ""
    }
}