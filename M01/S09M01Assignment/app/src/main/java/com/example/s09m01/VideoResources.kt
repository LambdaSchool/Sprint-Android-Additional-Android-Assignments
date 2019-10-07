package com.example.s09m01

import android.net.Uri
import kotlinx.android.synthetic.main.activity_local_media.*

object VideoResources {
    fun getLocalVideoUri(packageName: String): Uri? {
       return Uri.parse("android.resource://$packageName/R.raw.animal")
    }
}