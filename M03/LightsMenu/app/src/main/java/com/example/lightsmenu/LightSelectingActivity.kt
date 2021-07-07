package com.example.lightsmenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_light_selecting.*

class LightSelectingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_light_selecting)

        title = "Light Selecting"



        display_name.text = "Light Selecting"
    }
}
