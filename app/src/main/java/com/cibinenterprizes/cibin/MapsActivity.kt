package com.cibinenterprizes.cibin

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.cibinenterprizes.cibin.model.ContactDetailsOfCustomer
import com.cibinenterprizes.cibin.model.OrderVerification
import com.cibinenterprizes.cibin.model.PositionModel

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback
{
    private lateinit var mMap: GoogleMap
    private val LOCATION_PERMISSION_REQUEST = 1
    private val cal = Calendar.getInstance()
    private var markers: MutableList<Marker> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun getLocationAccess()
    {
        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            mMap.isMyLocationEnabled = true
        }else{
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
    {
        if(requestCode == LOCATION_PERMISSION_REQUEST)
        {
            if(grantResults.contains(PackageManager.PERMISSION_GRANTED))
            {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED)
                {
                    mMap.isMyLocationEnabled = true
                    return
                }

            }else{
                Toast.makeText(this,"User has not granted location access permission", Toast.LENGTH_LONG).show()
                finish()
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


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMapReady(googleMap: GoogleMap)
    {
        mMap = googleMap
        getLocationAccess()
        mMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(11.057492467264849,77.41389948874712)))
        mMap.setOnInfoWindowLongClickListener{
            markers.remove(it)
            it.remove()
        }
        mMap.setOnMapClickListener {latLng ->

            showAlertDialog(latLng)
        }

        // Add a marker in Sydney and move the camera
        /*val sydney = LatLng(11.057492467264849,77.41389948874712)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))*/

    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun showAlertDialog(latLng: LatLng)
    {
        val cal = Calendar.getInstance()

        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        val hour = cal.get(Calendar.HOUR_OF_DAY)
        val minute = cal.get(Calendar.MINUTE)

        val myLd = LocalDate.of(year, month, day)
        val myLt = LocalTime.of(hour, minute)

        val idAuth = FirebaseAuth.getInstance().currentUser?.uid.toString()
        val db = FirebaseDatabase.getInstance().reference
        val key = db.push().key.toString()
        val orderDetailForm = LayoutInflater.from(this).inflate(R.layout.order_details, null)
        val dialog = AlertDialog.Builder(this)
            .setTitle("ORDER REQUEST")
            .setView(orderDetailForm)
            .setNegativeButton("Cancel", null)
            .setPositiveButton("Ok", null)
            .show()
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener{

            val phone = orderDetailForm.findViewById<EditText>(R.id.phone_number).text.toString()
            val number_of_kg = orderDetailForm.findViewById<EditText>(R.id.kg_number).text.toString()
            val area = orderDetailForm.findViewById<EditText>(R.id.address).text.toString()
            val Details = ContactDetailsOfCustomer(area, "+91-"+phone, number_of_kg+" Kg")
            val marker = mMap.addMarker(MarkerOptions()
                .position(latLng)
                .title("ORDER REQUEST")
                .snippet(Details.toString()))
            markers.add(marker)
            val position = PositionModel(marker.position.latitude, marker.position.longitude)
            val orderVerification = OrderVerification(myLd.toString(), myLt.toString(), key, "Pending", Details, position)
            db.child("OrderList").child(myLd.toString()).child(idAuth).child(myLt.toString()).setValue(orderVerification).addOnCompleteListener {
                db.child("Verification").child(idAuth).child(key).setValue(orderVerification).addOnCompleteListener {
                    Toast.makeText(this,"Marker add successfully",Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, OrderListActivity::class.java))
                }
            }
            dialog.dismiss()
        }

    }
}
