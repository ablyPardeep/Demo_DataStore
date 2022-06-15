package com.demo.demoflow_datastore.model

import com.demo.demoflow_datastore.adapter.RecyclerAdapter

abstract class AbstractModel {

    var adapterPosition: Int = -1
    var onItemClick: RecyclerAdapter.OnItemClick? = null
}