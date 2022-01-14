package com.cibinenterprizes.cibin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cibinenterprizes.cibin.R
import com.cibinenterprizes.cibin.model.OrderVerification
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class myadapter(options: FirebaseRecyclerOptions<OrderVerification>) : FirebaseRecyclerAdapter<OrderVerification, myadapter.myviewholder>(options)
{

    class myviewholder : RecyclerView.ViewHolder{

        var date: TextView? = null
        var time: TextView? = null
        var orderkey: TextView? = null
        var verification: TextView? = null

        constructor(itemView: View) : super(itemView) {
            date = itemView.findViewById(R.id.date)
            time = itemView.findViewById(R.id.time)
            orderkey = itemView.findViewById(R.id.orderkey)
            verification = itemView.findViewById(R.id.verification)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {
        var view: View = LayoutInflater.from(parent.getContext()).inflate(R.layout.varification_order_list, parent, false)
        return  myviewholder(view)
    }

    override fun onBindViewHolder(holder: myviewholder, position: Int, model: OrderVerification) {
        holder.date?.setText(model.date)
        holder.time?.setText(model.time)
        holder.orderkey?.setText(model.orderKey)
        holder.verification?.setText(model.verification)

    }
}