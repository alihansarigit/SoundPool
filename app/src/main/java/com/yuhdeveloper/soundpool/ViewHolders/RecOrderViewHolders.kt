package com.yuhdeveloper.soundpool.ViewHolders

import android.annotation.SuppressLint
import android.media.SoundPool
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.android.billingclient.api.SkuDetails
import com.yuhdeveloper.soundpool.BillingPurchase
import com.yuhdeveloper.soundpool.IContact
import com.yuhdeveloper.soundpool.Pojos.Contents
import com.yuhdeveloper.soundpool.R


class RecOrderViewHolders(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener
{

    var txtOrderName:TextView?=null
    var imgOrderPic:ImageView?=null

    lateinit var skuDetails:SkuDetails
    override fun onClick(p0: View?) {

        var billing:BillingPurchase=BillingPurchase()
        billing.openOrder(skuDetails)

    }


    init {

        txtOrderName = itemView.findViewById(R.id.custom_rec_txtOrderName)
        imgOrderPic = itemView.findViewById(R.id.custom_rec_imgOrderPic)

        itemView.setOnClickListener(this)
    }
}

