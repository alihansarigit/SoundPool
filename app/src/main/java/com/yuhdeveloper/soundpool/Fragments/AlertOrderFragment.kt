package com.yuhdeveloper.soundpool.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yuhdeveloper.soundpool.Adapters.RecOrderAdapter
import com.yuhdeveloper.soundpool.BillingPurchase
import com.yuhdeveloper.soundpool.Pojos.OrderPojo

import com.yuhdeveloper.soundpool.R

/**
 * A simple [Fragment] subclass.
 */
class AlertOrderFragment : DialogFragment() {


    var rec_order:RecyclerView?=null
    lateinit var billing:BillingPurchase
    lateinit var orders:List<OrderPojo>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_alert_order, container, false)
        initStep(v)
        return v
    }

    private fun initStep(v: View) {
        rec_order = v.findViewById(R.id.frg_alertorder_rec)

        var adapter:RecOrderAdapter = RecOrderAdapter(orders)

        var layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rec_order!!.layoutManager = layoutManager
        rec_order!!.adapter = adapter

    }


}
