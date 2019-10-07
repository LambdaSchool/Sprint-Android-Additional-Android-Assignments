package com.example.assignmentt

import android.net.Uri

object VideoResources {
    fun getLocalVideoURI(packageName: String): String {
        return "android.resource://" + packageName + "/" + R.raw.animal
    }
}