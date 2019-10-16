package com.example.advancedkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        image.loadUrl("https://en.wikipedia.org/wiki/File:Trudeau_visit_White_House_for_USMCA_(cropped).jpg")
    }


}
