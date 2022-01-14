package com.cibinenterprizes.cibin

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_contact_us.*

class ContactUsActivity1 : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us)
        auth = FirebaseAuth.getInstance()
        val instagram = findViewById<ImageView>(R.id.instagram)
        val youtube = findViewById<ImageView>(R.id.youtube)
        val twitter = findViewById<ImageView>(R.id.twitter)
        val linkedin = findViewById<ImageView>(R.id.linkedin)
        val facebook = findViewById<ImageView>(R.id.facebook)

        instagram.setOnClickListener {
            gotoUrl("https://instagram.com/cibin_cigarette_recycling?igshid=gx4zoolh3h9c")
        }
        youtube.setOnClickListener {
            gotoUrl("https://www.youtube.com/channel/UCYzbghOUjyrYtsHrugdmTYw?view_as=subscriber")
        }
        twitter.setOnClickListener {
            gotoUrl("https://twitter.com/CEnterprizes")
        }
        linkedin.setOnClickListener {
            gotoUrl("https://www.linkedin.com/in/cibin-enterprizes-09831a1b7/")
        }
        facebook.setOnClickListener {
            gotoUrl("https://www.facebook.com/cibinenterprizes")
        }

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map_location) as SupportMapFragment
        mapFragment.getMapAsync(this)

        imageButton.setOnClickListener {
            val number: String = "9597417946"
            val intent = Intent(Intent.ACTION_CALL)
            intent.data=Uri.parse("tel: $number")
            startActivity(intent)
        }
        imageButton2.setOnClickListener {
            val recipient = "cibinenterprize@gmail.com"
            sendEmail(recipient)
        }
    }
    private fun sendEmail(recipient: String) {
        val mIntent = Intent(Intent.ACTION_SEND)
        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"
        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))

        try {
            startActivity(Intent.createChooser(mIntent, "Choose Email Client..."))
        }
        catch (e: Exception){
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }

    }

    private fun gotoUrl(s: String) {
        val uri = Uri.parse(s)
        startActivity(Intent(Intent.ACTION_VIEW, uri))

    }


    override fun onMapReady(googleMap: GoogleMap){
        mMap = googleMap
        val sydney = LatLng(11.057492467264849,77.41389948874712)
        mMap.addMarker(MarkerOptions().position(sydney).title("CIBIN Enterprizes"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId){
            R.id.home_page -> startActivity(Intent(this, MainActivity1::class.java))
            R.id.price_list -> onStartPrices()
            R.id.certificate -> onStartCertificate()
            R.id.request_order -> startActivity(Intent(this, MapsActivity::class.java))
            R.id.order_list -> startActivity(Intent(this, OrderListActivity::class.java))
            R.id.contact_us -> onStartContact()
            R.id.signout -> signoutOperation()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.getItem(1)?.isVisible=false
        return super.onPrepareOptionsMenu(menu)
    }
    fun signoutOperation(){
        auth.signOut()
        startActivity(Intent(this,MainActivity::class.java))

    }
    private fun onStartPrices() {
        super.onStart()
        val user = auth.currentUser

        if(user != null){
            startActivity(Intent(this, PriceListActivity1::class.java))

        }else{
            startActivity(Intent(this, PriceListActivity::class.java))

        }
    }
    private fun onStartCertificate() {
        super.onStart()
        val user = auth.currentUser

        if(user != null){
            startActivity(Intent(this,CompanyCertificates1::class.java))

        }else{
            startActivity(Intent(this, CompanyCertificates::class.java))

        }
    }
    private fun onStartContact() {
        super.onStart()
        val user = auth.currentUser

        if(user != null){
            startActivity(Intent(this,ContactUsActivity1::class.java))

        }else{
            startActivity(Intent(this, ContactUsActivity::class.java))

        }
    }
}