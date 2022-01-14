package com.cibinenterprizes.cibin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth

class CompanyCertificates : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_certificates)
        auth = FirebaseAuth.getInstance()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId){
            R.id.home_page -> startActivity(Intent(this, MainActivity::class.java))
            R.id.price_list -> onStartPrices()
            R.id.certificate -> onStartCertificate()
            R.id.contact_us -> onStartContact()
            R.id.register_login -> startActivity(Intent(this,RegisterActivity::class.java))
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.getItem(4)?.isVisible=false
        menu?.getItem(5)?.isVisible=false
        menu?.getItem(7)?.isVisible=false
        return super.onPrepareOptionsMenu(menu)
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