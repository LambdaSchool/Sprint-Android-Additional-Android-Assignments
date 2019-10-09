package com.example.maps

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback {

    private lateinit var mMap: GoogleMap
    private var currentLocation: Location? = null
    private val FINE_LOCATION_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        (map as SupportMapFragment).getMapAsync(this)

        btn_center_current_location.setOnClickListener {
            //center on user's location in the map fragment
            mMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(currentLocation.latitude, currentLocation.longitude)))
        }

        btn_add_pin.setOnClickListener {
            currentLocation?.let{
                mMap.addMarker(MarkerOptions().position(LatLng(it.latitude, it.longitude)).title("Current Location"))
            }
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        if(PermissionChecker.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PermissionChecker.PERMISSION_DENIED){
            //get the current location
            setCurrentLocation()
        } else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), FINE_LOCATION_REQUEST_CODE)
        }
        // Add a marker in Sydney and move the camera

        val sydney = LatLng(-34.0, 151.0)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    private fun setCurrentLocation(){
        LocationServices.getFusedLocationProviderClient(this)
            .lastLocation.addOnSuccessListener {
            currentLocation = it
            tv_current_location.text = "${tv_current_location.text} Latitude - ${currentLocation.latitude} Longitude - ${currentLocation.longitude}"
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(requestCode == FINE_LOCATION_REQUEST_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            //update current location in textview
            setCurrentLocation()
        } else if(grantResults[1] == PackageManager.PERMISSION_DENIED){
            Toast.makeText(this, "FAIL", Toast.LENGTH_SHORT).show()
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
