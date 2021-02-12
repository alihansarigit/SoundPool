package com.yuhdeveloper.soundpool

import android.app.Activity
import android.content.Context
import androidx.fragment.app.FragmentManager
import com.android.billingclient.api.*
import com.yuhdeveloper.soundpool.Fragments.AlertOrderFragment
import com.yuhdeveloper.soundpool.Pojos.OrderPojo

class BillingPurchase() : PurchasesUpdatedListener {

    override fun onPurchasesUpdated(responseCode: Int, purchases: MutableList<Purchase>?) {
        allowMultiplePurchases(purchases)
    }


    lateinit var list: List<SkuDetails>
    var fragmentManager: FragmentManager?=null
    private val skuList = listOf("relax_1", "relax_2", "relax_3")





    companion object {
        var billingClient: BillingClient?=null
         lateinit var context:Context
    }

    fun openOrder(order: SkuDetails) {
        val billingFlowParams = BillingFlowParams
                .newBuilder()
                .setSkuDetails(order)
                .build()
        billingClient!!.launchBillingFlow(context as Activity?, billingFlowParams)
    }

     fun setupBillingClient() {
        billingClient = BillingClient
                .newBuilder(context)
                .setListener(this)
                .build()

        billingClient!!.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(@BillingClient.BillingResponse billingResponseCode: Int) {
                if (billingResponseCode == BillingClient.BillingResponse.OK) {
                    println("BILLING | startConnection | RESULT OK")
                } else {
                    println("BILLING | startConnection | RESULT: $billingResponseCode")
                }
            }

            override fun onBillingServiceDisconnected() {
                println("BILLING | onBillingServiceDisconnected | DISCONNECTED")
            }
        })
    }

    fun onLoadProductsClicked(billing: BillingPurchase) {
        if (billingClient!!.isReady) {
            val params = SkuDetailsParams
                    .newBuilder()
                    .setSkusList(skuList)
                    .setType(BillingClient.SkuType.INAPP)
                    .build()

            billingClient!!.querySkuDetailsAsync(params) { responseCode, skuDetailsList ->
                if (responseCode == BillingClient.BillingResponse.OK) {
                    println("querySkuDetailsAsync, responseCode: $responseCode")

                    var orderPojo: ArrayList<OrderPojo> = ArrayList<OrderPojo>()

                    for (a in skuDetailsList) {
                        var order: OrderPojo = OrderPojo()
                        order.orderDetails = a
                        orderPojo!!.add(order)
                    }

                    orderPojo!!.get(0).orderPic = R.drawable.doughnut
                    orderPojo!!.get(1).orderPic = R.drawable.coffe
                    orderPojo!!.get(2).orderPic = R.drawable.groceries

                    var fragment = AlertOrderFragment()
                    fragment.orders = orderPojo

                    fragment.show(fragmentManager, "order")

                } else {
                    println("Can't querySkuDetailsAsync, responseCode: $responseCode")
                }
            }
        } else {
            println("Billing Client not ready")
        }
    }


    private fun allowMultiplePurchases(purchases: MutableList<Purchase>?) {
        val purchase = purchases?.first()
        if (purchase != null) {
            billingClient!!.consumeAsync(purchase.purchaseToken) { responseCode, purchaseToken ->
                if (responseCode == BillingClient.BillingResponse.OK && purchaseToken != null) {
                    println("AllowMultiplePurchases success, responseCode: $responseCode")
                    clearHistory()
                } else {
                    println("Can't allowMultiplePurchases, responseCode: $responseCode")
                }
            }
        }
    }

    fun clearHistory() {
        billingClient!!.queryPurchases(BillingClient.SkuType.INAPP).purchasesList
                .forEach {
                    billingClient!!.consumeAsync(it.purchaseToken) { responseCode, purchaseToken ->
                        if (responseCode == BillingClient.BillingResponse.OK && purchaseToken != null) {
                            println("onPurchases Updated consumeAsync, purchases token removed: $purchaseToken")
                        } else {
                            println("onPurchases some troubles happened: $responseCode")
                        }
                    }
                }

    }



}


