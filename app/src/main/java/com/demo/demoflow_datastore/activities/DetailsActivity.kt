package com.demo.demoflow_datastore.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import com.bumptech.glide.Glide
import com.demo.demoflow_datastore.R
import com.demo.demoflow_datastore.util.DataStorePrefs
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.coroutines.flow.map

class DetailsActivity : AppCompatActivity() {

    private lateinit var userDetailDataStore: DataStorePrefs
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        userDetailDataStore = DataStorePrefs(this)

        userDetailDataStore.dataStore.data.map { it }.asLiveData().observe(this) {
            tvName.text = it.name
            tvOrigin.text = it.country
            Glide.with(this).load(it.picture).placeholder(R.mipmap.ic_launcher).into(imageLogo)
        }
    }
}