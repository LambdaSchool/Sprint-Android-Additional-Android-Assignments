package com.example.notetakerwmenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.notetakerwmenu.fragments.ViewFragment

class
MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(fragment = ViewFragment())
    }

    private fun loadFragment(fragment: Fragment): Boolean {

        if (fragment != null) {

            supportFragmentManager.beginTransaction().replace(R.id.fl_container, fragment)
                .commit()

            return true

        }

        return loadFragment(fragment)
    }
}

