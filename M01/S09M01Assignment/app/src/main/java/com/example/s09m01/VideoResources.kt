package com.example.s09m01

import android.net.Uri

object VideoResources {
    fun getLocalVideoUri(packageName: String): Uri? {
       return Uri.parse("android.resource://$packageName/R.raw.animal")
    }
}