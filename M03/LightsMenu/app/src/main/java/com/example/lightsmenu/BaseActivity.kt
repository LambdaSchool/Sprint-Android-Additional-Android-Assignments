package com.example.lightsmenu

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar

import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class BaseActivity : AppCompatActivity() {


    protected var toolbar: Toolbar? = null
    protected  var drawerLayout: DrawerLayout? = null
    protected var frameLayout: FrameLayout? = null
    protected var context: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        context = this
    }
    override fun setContentView(layoutResID: Int) {
        val layout = layoutInflater.inflate(R.layout.drawer_layout, null)
        drawerLayout = layout.findViewById(R.id.drawer_layout)
        frameLayout = drawerLayout?.findViewById(R.id.frame_layout)
        layoutInflater.inflate(layoutResID, frameLayout, true)

        toolbar = drawerLayout?.findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer)
        drawerLayout?.addDrawerListener(toggle)
        toggle.syncState()
        val navView = drawerLayout?.findViewById<NavigationView>(R.id.nav_view)

        navView?.setNavigationItemSelectedListener {
            it.isChecked = true
            when(it.itemId){
                R.id.controller -> startActivity(Intent(this, ControllerConfigurationActivity::class.java))
                R.id.light_config -> startActivity(Intent(this, LightConfigurationActivity::class.java))
                R.id.light_select -> startActivity(Intent(this, LightSelectingActivity::class.java))
                R.id.dark_mode -> Toast.makeText(this, "dark mode activated", Toast.LENGTH_SHORT).show()
            }
            drawerLayout?.closeDrawers()
            true
        }
        super.setContentView(layout)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.light_off -> Toast.makeText(this, "lights are off", Toast.LENGTH_SHORT).show()
            R.id.add_light -> Toast.makeText(this, "added new light", Toast.LENGTH_SHORT).show()
            R.id.toggle_count -> Toast.makeText(this, "toggled", Toast.LENGTH_SHORT).show()
            R.id.sort -> Toast.makeText(this, "sorted", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}
