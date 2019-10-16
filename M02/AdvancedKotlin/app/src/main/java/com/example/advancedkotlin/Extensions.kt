package com.example.advancedkotlin

import android.app.Notification
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.core.app.NotificationCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

fun Notification.create(context: Context): NotificationCompat.Builder {

    return NotificationCompat.Builder(context, "")
        .setContentText("This is my Notification.")
        .setSmallIcon(R.drawable.ic_launcher_foreground)
}

fun ImageView.loadUrl(url: String){
    Glide.with(this)
        .load(url)
        .onSuccess {
            Log.i("HIMAN", "123")
        }
        .onFailure {
            Log.i("SHIN", "BANNED")
        }
        .into(this)
}

fun RequestBuilder<Drawable>.onSuccess(onSuccess: () -> Unit): RequestBuilder<Drawable>{
    this.addListener(object : RequestListener<Drawable> {
        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
            return false
        }
        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
            onSuccess.invoke()
            return true
        }
    })
    return this
}

fun RequestBuilder<Drawable>.onFailure(onFailure: () -> Unit): RequestBuilder<Drawable>{
    this.addListener(object: RequestListener<Drawable>{
        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
            onFailure.invoke()
            return true
        }

        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
            return false
        }
    })
    return this
}