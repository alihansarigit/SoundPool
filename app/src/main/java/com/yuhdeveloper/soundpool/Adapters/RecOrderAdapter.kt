package com.yuhdeveloper.soundpool.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.billingclient.api.SkuDetails
import com.yuhdeveloper.soundpool.BillingPurchase
import com.yuhdeveloper.soundpool.Pojos.OrderPojo
import com.yuhdeveloper.soundpool.R
import com.yuhdeveloper.soundpool.ViewHolders.RecOrderViewHolders


class RecOrderAdapter(order: List<OrderPojo>) : RecyclerView.Adapter<RecOrderViewHolders>() {
    var orders: List<OrderPojo>



    init {
        orders = order
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecOrderViewHolders {
        var v: View = LayoutInflater.from(parent.context!!).inflate(R.layout.custom_rec_order, parent, false)
        return RecOrderViewHolders(v)
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    override fun onBindViewHolder(holder: RecOrderViewHolders, position: Int) {

        holder.imgOrderPic!!.setImageResource(orders.get(position).orderPic)
        holder.txtOrderName!!.setText(orders.get(position).orderDetails.description)
        holder.skuDetails = orders.get(position).orderDetails
    }
}