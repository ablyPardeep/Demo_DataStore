package com.demo.demoflow_datastore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import com.demo.demoflow_datastore.databinding.ActivityMainBinding
import com.demo.demoflow_datastore.viewmodel.ListVM


class MainActivity : AppCompatActivity() {

    private val listVm : ListVM by viewModels()
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.listVM = listVm
    }


    override fun onResume() {
        super.onResume()
        listVm.eventListApi(this)
    }
}