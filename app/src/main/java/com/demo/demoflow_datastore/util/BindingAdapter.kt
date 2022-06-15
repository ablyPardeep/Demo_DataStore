package com.demo.demoflow_datastore.util

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.demo.demoflow_datastore.R

object BindingAdapter {

    @androidx.databinding.BindingAdapter(value = ["setRecyclerAdapter"], requireAll = false)
    @JvmStatic
    fun setRecyclerAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        recyclerView.adapter = adapter
    }

    // method for image with url
    @androidx.databinding.BindingAdapter(value = ["setImageSrcWithUrl"], requireAll = false)
    @JvmStatic
    fun setImageSrcWithUrl(
        ivImage: ImageView,
        str: String?

    ) {
        try {
            Glide.with(ivImage.context)
                .asBitmap().load(str)
                .apply(RequestOptions().override(600, 200))
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        ivImage.setImageBitmap(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {

                    }

                    override fun onLoadFailed(errorDrawable: Drawable?) {
                        Log.e("Image", "LoadingFailed")
                        ivImage.setImageResource(R.drawable.ic_launcher_foreground)
                    }

                    override fun onDestroy() {
                        Log.e("Image", "destroyed")
                        ivImage.setImageResource(R.drawable.ic_launcher_foreground)
                    }
                })

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}