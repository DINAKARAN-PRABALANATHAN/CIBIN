package com.cibinenterprizes.cibin

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import com.cibinenterprizes.cibin.adapters.TrashAdapters
import com.cibinenterprizes.cibin.model.TrashItems
import com.google.firebase.auth.FirebaseAuth

class PriceListActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    //Card view codes
    private var arrayList:ArrayList<TrashItems>? = null
    private var gridView:GridView? = null
    private var trashAdapters:TrashAdapters? = null

    //Card view codes end


    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_price_list)
        //Card view codes
        gridView = findViewById(R.id.price_list_gridview)
        arrayList = ArrayList()
        arrayList = setDataList()
        trashAdapters = TrashAdapters(applicationContext, arrayList!!)
        gridView?.adapter = trashAdapters
        gridView?.onItemClickListener = this

        //Card view codes end
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

        }else {
            startActivity(Intent(this, ContactUsActivity::class.java))

        }
    }

    //Card view codes

    private fun setDataList() : ArrayList<TrashItems>{
        var arrayList:ArrayList<TrashItems> = ArrayList()

        arrayList.add(TrashItems(R.drawable.cigarette_butt, "Cigarette Butt", "Rs.100/kg"))
        arrayList.add(TrashItems(R.drawable.newspaper, "News Papers", "Rs.10/kg"))
        arrayList.add(TrashItems(R.drawable.iron, "Irons", "Rs.12/kg"))
        arrayList.add(TrashItems(R.drawable.notebook, "Hand Written Notes", "Rs.10/kg"))
        arrayList.add(TrashItems(R.drawable.plastic, "Mixed Plastics", "Rs.5/kg"))
        arrayList.add(TrashItems(R.drawable.cardboard, "Card Boards", "Rs.4/kg"))
        arrayList.add(TrashItems(R.drawable.aluminium, "Aluminium", "Rs.65/kg"))
        arrayList.add(TrashItems(R.drawable.brass, "Brass(Alloys)", "Rs.200/kg"))
        arrayList.add(TrashItems(R.drawable.copper, "Coppers", "Rs.300/kg"))
        arrayList.add(TrashItems(R.drawable.stainless_steel, "Stainless Steel", "Rs.23/kg"))
        arrayList.add(TrashItems(R.drawable.air_conditioner, "Air Conditioner(1.5Ton)", "Rs.1700/pc"))
        arrayList.add(TrashItems(R.drawable.air_conditioner, "Air Conditioner(1Ton)", "Rs.1300/pc"))
        arrayList.add(TrashItems(R.drawable.fridge, "Fridges(Single Door)", "Rs.400/pc"))
        arrayList.add(TrashItems(R.drawable.fridge, "Fridges(Double Door)", "Rs.600/pc"))
        arrayList.add(TrashItems(R.drawable.washing_machine, "Washing Machine (Top load)", "Rs.380/pc"))
        arrayList.add(TrashItems(R.drawable.washing_machine, "Washing Machine (Front load)", "Rs.600/pc"))
        arrayList.add(TrashItems(R.drawable.electric_motor, "Motor and Pump", "Rs.25/kg"))
        arrayList.add(TrashItems(R.drawable.e_waste, "E Waste", "Rs.8/kg"))
        arrayList.add(TrashItems(R.drawable.battery, "Battery", "Rs.45/kg"))
        arrayList.add(TrashItems(R.drawable.cpu, "CPU", "Rs.150/kg"))
        arrayList.add(TrashItems(R.drawable.book, "Books", "Rs.8/kg"))
        arrayList.add(TrashItems(R.drawable.television, "LCD & LED TV", "Rs.75/kg"))
        arrayList.add(TrashItems(R.drawable.wiring, "Wires", "Rs.40/kg"))
        arrayList.add(TrashItems(R.drawable.toy, "Toys", "Rs.2/kg"))
        arrayList.add(TrashItems(R.drawable.glass_bottle, "Glass Bottle", "Rs.0/pc"))



        return arrayList
    }


    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        var items:TrashItems = arrayList!!.get(p2)
        Toast.makeText(applicationContext, items.name, Toast.LENGTH_LONG).show()
    }

    //Card view codes end

}