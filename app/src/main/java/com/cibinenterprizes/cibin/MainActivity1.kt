package com.cibinenterprizes.cibin

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.network_alart_dialog.*

class MainActivity1 : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)
        auth = FirebaseAuth.getInstance()

        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.setLogo(R.drawable.action_bar)
        actionBar?.setDisplayUseLogoEnabled(true)

        //code
        checkConnection()
        //code
    }

    private fun checkConnection() {

        val manager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = manager.activeNetworkInfo

        if (null != networkInfo){
            if(networkInfo.type == ConnectivityManager.TYPE_WIFI){
                Toast.makeText(this, "Wifi Connected",Toast.LENGTH_SHORT).show()
            }else if (networkInfo.type == ConnectivityManager.TYPE_MOBILE){
                Toast.makeText(this, "Mobile Connected",Toast.LENGTH_SHORT).show()
            }
        }else{
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.network_alart_dialog)

            dialog.setCanceledOnTouchOutside(false)

            dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT)

            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            dialog.btn_try_again.setOnClickListener {
                recreate()
            }
            dialog.show()
        }
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