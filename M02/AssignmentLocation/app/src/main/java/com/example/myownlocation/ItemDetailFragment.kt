package com.example.myownlocation

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.myownlocation.dummy.DummyContent
import com.example.myownlocation.model.MeModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.item_detail.view.*

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListActivity]
 * in two-pane mode (on tablets) or a [ItemDetailActivity]
 * on handsets.
 */
class ItemDetailFragment : Fragment(), OnMapReadyCallback {
    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap

        val lat = item?.location?.coordinates?.lattitude ?: 0.0
        val lon = item?.location?.coordinates?.longtitude ?: 0.0
        val city = item?.location?.city ?: "unknown city"
        val latlon = LatLng(lat, lon)
        // Using known world locations, since lat/lng from API call is random (and usually in the middle of the Pacific...)
        val location = MY_LOCATION.random()
        // TODO: S09M02-9 use location data to move the camera and place a pin
        googleMap?.addMarker(MarkerOptions().position(location).title("Marker in $city"))
        googleMap?.moveCamera(CameraUpdateFactory.newLatLng(location))    }

    /**
     * The dummy content this fragment is presenting.
     */
    var mMap: GoogleMap? = null
    // private var item: DummyContent.DummyItem? = null
    private var item: MeModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                item = it.getSerializable(ARG_ITEM_ID) as MeModel
            //    activity?.toolbar_layout?.title = item?.name?.first
                if (activity is ItemDetailActivity)
                    activity?.toolbar_layout?.title = item?.getFullName()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)

        // Show the dummy content as text in a TextView.
        item?.let {
            rootView.item_detail.text = item?.getFullName()
        }
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        getCurrentLocation()

        return rootView
    }
    private fun getCurrentLocation(){
        if (ContextCompat.checkSelfPermission(context!!, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            // request the permission
            ActivityCompat.requestPermissions(
                activity!!,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                FINE_LOCATION_REQUEST_CODE
            )
        } else {
            getLocation()
        }
    }
    private fun getLocation(){
        if (ActivityCompat.checkSelfPermission(
                context!!,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        val locationProviderClient = LocationServices.getFusedLocationProviderClient(context!!)
        locationProviderClient.lastLocation.addOnSuccessListener { location ->
            val contactLocation = Location("contact")

            contactLocation.latitude = item!!.location.coordinates.lattitude
            contactLocation.longitude = item!!.location.coordinates.longtitude

            val distance = location.distanceTo(contactLocation)

            bot_item_detail.text =
                getString(R.string.distance, (distance / 1000))
            }

        }


    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
        private const val FINE_LOCATION_REQUEST_CODE = 5
        private val MY_LOCATION = listOf(LatLng(0.0, 0.0))
    }
}
