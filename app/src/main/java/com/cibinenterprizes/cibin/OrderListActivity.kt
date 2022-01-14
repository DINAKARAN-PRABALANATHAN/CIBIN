package com.cibinenterprizes.cibin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibinenterprizes.cibin.adapters.myadapter
import com.cibinenterprizes.cibin.model.OrderVerification
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class OrderListActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    //code
    private lateinit var recview: RecyclerView
    lateinit var adapter: myadapter
    //code


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_list)
        auth = FirebaseAuth.getInstance()
        val idAuth = FirebaseAuth.getInstance().currentUser?.uid.toString()

        //code
        recview = findViewById(R.id.order_list_view)
        recview.setLayoutManager(LinearLayoutManager(this))

        val options: FirebaseRecyclerOptions<OrderVerification> = FirebaseRecyclerOptions.Builder<OrderVerification>()
            .setQuery(FirebaseDatabase.getInstance().getReference().child("Verification").child(idAuth), OrderVerification::class.java).build()
        adapter = myadapter(options)
        recview.setAdapter(adapter)
        //code

    }
    //code
    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }
    //code

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