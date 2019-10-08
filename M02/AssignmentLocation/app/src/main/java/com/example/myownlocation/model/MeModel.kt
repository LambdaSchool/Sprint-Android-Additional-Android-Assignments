package com.example.myownlocation.model

import java.io.Serializable

data class MyName(val first: String, val last: String):Serializable

data class MyLocationStreet(val number: Int, val name: String): Serializable

data class MyLocationCoordinates(val lattitude: Double, val longtitude: Double): Serializable

data class MyLocation(
    val street: MyLocationStreet,
    val city: String,
    val state: String,
    val postcode: String,
    val coordinates: MyLocationCoordinates
) : Serializable
data class MeModel(
    val name: MyName,
    val location: MyLocation
) : Serializable {
    fun getFullName(): String = "${name.first} ${name.last}"
}
data class ContactResults(val results: List<MeModel>) : Serializable