package com.cibinenterprizes.cibin.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.cibinenterprizes.cibin.R
import com.cibinenterprizes.cibin.model.TrashItems

class TrashAdapters(var context: Context, var arrayList: ArrayList<TrashItems>): BaseAdapter() {

    override fun getItem(p0: Int): Any {
        return arrayList.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var view:View = View.inflate(context, R.layout.card_view_price_list, null)
        var icons:ImageView = view.findViewById(R.id.icons)
        var names:TextView = view.findViewById(R.id.trash_name)
        var names1:TextView = view.findViewById(R.id.trash_prices)

        var listItem:TrashItems = arrayList.get(p0)
        icons.setImageResource(listItem.icons!!)
        names.text =listItem.name
        names1.text =listItem.name1

        return view
    }


}

