package com.demo.demoflow_datastore.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class RequestModel : ArrayList<RequestModelItem>()
@Parcelize

data class RequestModelItem(

    val image: Image,
    val name: String,
    val origin: String,

) : AbstractModel(),Parcelable
@Parcelize
data class Image(
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
):AbstractModel(),Parcelable
