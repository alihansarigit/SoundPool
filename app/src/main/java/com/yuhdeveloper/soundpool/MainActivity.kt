package com.yuhdeveloper.soundpool

import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.media.SoundPool
import android.os.Build
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.android.billingclient.api.*
import com.google.android.gms.ads.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.yuhdeveloper.soundpool.Adapters.ViewPagerAdapter
import com.yuhdeveloper.soundpool.Fragments.NaturalFragment
import com.yuhdeveloper.soundpool.Pojos.Categories
import com.yuhdeveloper.soundpool.Pojos.Contents


class MainActivity : AppCompatActivity(), View.OnClickListener, IContact,
        TabLayout.OnTabSelectedListener, SoundPool.OnLoadCompleteListener, PurchasesUpdatedListener {
    override fun onPurchasesUpdated(responseCode: Int, purchases: MutableList<Purchase>?) {
        println("onPurchasesUpdated: $responseCode")
        allowMultiplePurchases(purchases)
    }

    override fun changeVolume(_volume: Float, _content: Contents) {
//        changeVolume(_content,_volume)
        if (_content.streamID != null) {
            _content.streamID!!.setVolume(_volume.toFloat(), _volume.toFloat())
        }
    }


    override fun isActivated(click: Boolean, _content: Contents) {

        if (_content.isActive) {
            play(_content)
        } else {
            stop(_content)
        }

    }

    override fun onTabReselected(p0: TabLayout.Tab?) {
    }

    override fun onTabUnselected(p0: TabLayout.Tab?) {
        var drawable = p0?.customView!!.findViewById(R.id.custom_tabItem_imgPic) as ImageView
        drawable.clearColorFilter()
    }


    override fun onTabSelected(p0: TabLayout.Tab?) {
        var drawable = p0?.customView!!.findViewById(R.id.custom_tabItem_imgPic) as ImageView
        drawable.setColorFilter(ContextCompat.getColor(this, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);

        if (p0?.position == 0) {
            imgBackground!!.setImageResource(R.drawable.background)
        } else if (p0?.position == 1) {
            imgBackground!!.setImageResource(R.drawable.background_water)
        } else if (p0?.position == 2) {
            imgBackground!!.setImageResource(R.drawable.background_cakra)
        } else if (p0?.position == 3) {
            imgBackground!!.setImageResource(R.drawable.background_guitar)
        }

    }


    override fun onClick(p0: View?) {

//        if(p0?.getId()==R.id.btn1){
//            play(R.raw.animal_squal)
//        }
//        else if(p0?.getId()==R.id.btn2){
//            play(R.raw.anime_growl)
//
//        }
//        else if(p0?.getId()==R.id.btn3){
//            play(R.raw.cat_purr)
//
//        }
    }

    private val NR_OF_SIMULTANEOUS_SOUNDS = 7
    private val LEFT_VOLUME = 1.0f
    private val RIGHT_VOLUME = 1.0f
    private val NO_LOOP = -1
    private val PRIORITY = 0
    private val NORMAL_PLAY_RATE = 1.0f

    private var mSoundPool: SoundPool? = null
    private var mSoundPool2: SoundPool? = null
    private var mCSoundId: Int = 0
    private var mDSoundId: Int = 0
    private var mESoundId: Int = 0
    private var mFSoundId: Int = 0
    private var mGSoundId: Int = 0
    private var mASoundId: Int = 0
    private var mBSoundId: Int = 0


    private var btn1: Button? = null
    private var btn2: Button? = null
    private lateinit var btn3: Button

    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null
    private var lstTitle: ArrayList<String>? = null
    private var lstFragment: ArrayList<Fragment>? = null
    private var lstImage: ArrayList<Int>? = null

    var natureFragment: NaturalFragment? = null
    var enstrumentalFragment: NaturalFragment? = null
    var sualtiFragment: NaturalFragment? = null
    var homeFragment: NaturalFragment? = null

    var imgBackground: ImageView? = null
    var adapter: ViewPagerAdapter? = null
    var mediaPlayer: MediaPlayer? = null

    fun natureContentFill(): ArrayList<Contents> {
        var contents: ArrayList<Contents> = ArrayList<Contents>()
        contents.add(Contents(0, getString(R.string.natural_owl), R.drawable.baykus, Categories.doğa.toString(), R.raw.owl))
        contents.add(Contents(1, getString(R.string.natural_grasshopper), R.drawable.circirbocegi, Categories.doğa.toString(), R.raw.crickets))
        contents.add(Contents(2, getString(R.string.natural_campfire), R.drawable.kampatesi, Categories.doğa.toString(), R.raw.campfiree))
        contents.add(Contents(3, getString(R.string.natural_frog), R.drawable.kurbaga, Categories.doğa.toString(), R.raw.frog))
        contents.add(Contents(4, getString(R.string.natural_rain), R.drawable.yagmur, Categories.doğa.toString(), R.raw.rain))
        contents.add(Contents(5, getString(R.string.natural_storm), R.drawable.gokgurultusu, Categories.doğa.toString(), R.raw.storm))
        contents.add(Contents(6, getString(R.string.natural_birds), R.drawable.kussesi, Categories.doğa.toString(), R.raw.birds))
        contents.add(Contents(7, getString(R.string.natural_raintent), R.drawable.cadiryagmur, Categories.doğa.toString(), R.raw.raintent))
        contents.add(Contents(8, getString(R.string.natural_waterfall), R.drawable.selale, Categories.doğa.toString(), R.raw.waterfall))
        contents.add(Contents(9, getString(R.string.natural_forest), R.drawable.forest, Categories.doğa.toString(), R.raw.forest))
        contents.add(Contents(10, getString(R.string.natural_woodspecker), R.drawable.woodpecker, Categories.doğa.toString(), R.raw.woodpecker))
        contents.add(Contents(11, getString(R.string.natural_leaf), R.drawable.agacsesi, Categories.doğa.toString(), R.raw.rustleleaf))
        return contents
    }

    fun homeContentFill(): ArrayList<Contents> {
        var contents: ArrayList<Contents> = ArrayList<Contents>()
        contents.add(Contents(0, getString(R.string.home_supurge), R.drawable.supurge, Categories.ev.toString(), R.raw.supurgesesi))
        contents.add(Contents(1, getString(R.string.home_fonmakinesi), R.drawable.fonmakinesi, Categories.ev.toString(), R.raw.fonmakinesi))
        contents.add(Contents(2, getString(R.string.home_cakmak), R.drawable.cakmaksesi, Categories.ev.toString(), R.raw.cakmaksesi))
        contents.add(Contents(3, getString(R.string.home_bicak), R.drawable.bicakdograma, Categories.ev.toString(), R.raw.bicakdograma))
        contents.add(Contents(4, getString(R.string.home_bossise), R.drawable.bossise, Categories.ev.toString(), R.raw.bossise))

        contents.add(Contents(5, getString(R.string.home_gaz), R.drawable.gazsesi, Categories.ev.toString(), R.raw.gazsesi))
        contents.add(Contents(6, getString(R.string.home_kase), R.drawable.kase, Categories.ev.toString(), R.raw.kase))
        contents.add(Contents(7, getString(R.string.home_saat), R.drawable.saattiktak, Categories.ev.toString(), R.raw.saattiktak))
        contents.add(Contents(8, getString(R.string.home_lamba), R.drawable.dugmesesi, Categories.ev.toString(), R.raw.dugmesesi))
        return contents
    }

    fun enstrumentalContentFill(): ArrayList<Contents> {
        var contents: ArrayList<Contents> = ArrayList<Contents>()
        contents.add(Contents(0, getString(R.string.ens_gitar), R.drawable.guitar, Categories.ensrümental.toString(), R.raw.guitar))
        contents.add(Contents(1, getString(R.string.ens_flut), R.drawable.flute, Categories.ensrümental.toString(), R.raw.flute))
        contents.add(Contents(2, getString(R.string.ens_ruzgarcani), R.drawable.windbell, Categories.ensrümental.toString(), R.raw.windbell))
        contents.add(Contents(3, getString(R.string.ens_piyano), R.drawable.piano, Categories.ensrümental.toString(), R.raw.piano))
        contents.add(Contents(4, getString(R.string.ens_tibetflut), R.drawable.tibet_flute, Categories.ensrümental.toString(), R.raw.tibet_flute))
        contents.add(Contents(5, getString(R.string.ens_tibetkasesi), R.drawable.tibet_bowl, Categories.ensrümental.toString(), R.raw.tibet_bowl))
        return contents
    }

    fun underWaterContentFill(): ArrayList<Contents> {
        var contents: ArrayList<Contents> = ArrayList<Contents>()
        contents.add(Contents(0, getString(R.string.underwater_uw1), R.drawable.underwater, Categories.sualtı.toString(), R.raw.underwater))
        contents.add(Contents(1, getString(R.string.underwater_uw2), R.drawable.underwater_2, Categories.sualtı.toString(), R.raw.underwater_2))
        contents.add(Contents(2, getString(R.string.underwater_uwb1), R.drawable.underwater_bubble, Categories.sualtı.toString(), R.raw.underwater_bubble))
        contents.add(Contents(3, getString(R.string.underwater_uwb2), R.drawable.underwater_bubble_2, Categories.sualtı.toString(), R.raw.underwater_bubble_2))
        return contents
    }

    fun lstFill() {
        natureFragment = NaturalFragment()
        sualtiFragment = NaturalFragment()
        homeFragment = NaturalFragment()
        enstrumentalFragment = NaturalFragment()

        natureFragment!!.tagname = Categories.doğa.toString()
        sualtiFragment!!.tagname = Categories.sualtı.toString()
        homeFragment!!.tagname = Categories.ev.toString()
        enstrumentalFragment!!.tagname = Categories.ensrümental.toString()

        lstTitle!!.add(getString(R.string.category_natural))
        lstFragment!!.add(natureFragment!!)
        natureFragment!!.contents = natureContentFill()
        lstImage!!.add(R.drawable.category_doga)


        lstTitle!!.add(getString(R.string.category_waterdeep))
        lstFragment!!.add(sualtiFragment!!)
        sualtiFragment!!.contents = underWaterContentFill()
        lstImage!!.add(R.drawable.category_sualti)

        lstTitle!!.add(getString(R.string.category_home))
        lstFragment!!.add(homeFragment!!)
        homeFragment!!.contents = homeContentFill()
        lstImage!!.add(R.drawable.category_ev)

        lstTitle!!.add(getString(R.string.category_enstrumental))
        lstFragment!!.add(enstrumentalFragment!!)
        enstrumentalFragment!!.contents = enstrumentalContentFill()
        lstImage!!.add(R.drawable.category_entrumental)

        adapter = ViewPagerAdapter(supportFragmentManager, lstTitle!!, lstFragment!!, lstImage!!)
        viewPager!!.adapter = adapter
        tabLayout!!.setupWithViewPager(viewPager!!)
    }


    fun admob() {

        val adView: AdView = findViewById(R.id.nativex)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)


        adView.adListener = object : AdListener() {
            override fun onAdFailedToLoad(p0: Int) {
                super.onAdFailedToLoad(p0)
                adView.loadAd(adRequest)

            }

            override fun onAdLoaded() {
                super.onAdLoaded()

            }
        }
    }

    private val skuList = listOf("relax_1","relax_2","relax_3","relax_4")
/////////////
    var billingClient: BillingClient?=null
    private fun setupBillingClient() {
        billingClient = BillingClient
                .newBuilder(this)
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
    fun onLoadProductsClicked() {
        if (billingClient!!.isReady) {
            val params = SkuDetailsParams
                    .newBuilder()
                    .setSkusList(skuList)
                    .setType(BillingClient.SkuType.INAPP)
                    .build()

            billingClient!!.querySkuDetailsAsync(params) { responseCode, skuDetailsList ->
                if (responseCode == BillingClient.BillingResponse.OK) {
                    println("querySkuDetailsAsync, responseCode: $responseCode")
                    Toast.makeText(this, skuDetailsList[3].title, Toast.LENGTH_LONG).show()
                    val billingFlowParams = BillingFlowParams
                            .newBuilder()
                            .setSkuDetails(skuDetailsList[3])
                            .build()
                    billingClient!!.launchBillingFlow(this, billingFlowParams)
//                    initProductAdapter(skuDetailsList)
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
    private fun clearHistory() {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MobileAds.initialize(this) {}
        imgBackground = findViewById(R.id.main_imgBackground)
        imgBackground!!.setImageResource(R.drawable.background)

        tabLayout = findViewById(R.id.main_tabLayout)
        viewPager = findViewById(R.id.main_viewPager)
        var fab_donate: Button = findViewById(R.id.donate);
//        setupBillingClient()
//        clearHistory()

        var billing = BillingPurchase()
        BillingPurchase.context=this
        billing.fragmentManager = supportFragmentManager
        billing.setupBillingClient()

        fab_donate.setOnClickListener(View.OnClickListener {

            billing.onLoadProductsClicked(billing)


//            onLoadProductsClicked()

        });
        lstTitle = ArrayList<String>()
        lstFragment = ArrayList<Fragment>()
        lstImage = ArrayList<Int>()


        lstFill()

        for (i in 0..tabLayout!!.tabCount - 1) {
            tabLayout!!.getTabAt(i)!!.setCustomView(adapter!!.getTabView(i, this))
        }

        var drawable = tabLayout!!.getTabAt(0)?.customView!!.findViewById(R.id.custom_tabItem_imgPic) as ImageView
        drawable.setColorFilter(ContextCompat.getColor(this, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);

        tabLayout!!.addOnTabSelectedListener(this)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mSoundPool = SoundPool.Builder().setMaxStreams(5).setAudioAttributes(
                    AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                            .build()
            ).build()
        } else {
            mSoundPool = SoundPool(5, AudioManager.STREAM_MUSIC, 0)
        }
        admob()
    }


    fun play(content: Contents) {
        if (content.streamID == null) {
            mediaPlayer = MediaPlayer.create(this, content.music)
            mediaPlayer!!.setVolume(content.volume.toFloat(), content.volume.toFloat())
            mediaPlayer!!.isLooping = true
            mediaPlayer!!.start()
            content.streamID = mediaPlayer
        }
    }

    fun changeVolume(content: Contents, _volume: Int) {
        if (content.streamID != null) {
            content.streamID!!.setVolume(_volume.toFloat(), _volume.toFloat())
        }
    }

    fun stop(content: Contents) {
        content.streamID!!.stop()
        content.streamID!!.release()
        content.streamID = null
    }

    override fun onLoadComplete(p0: SoundPool?, p1: Int, p2: Int) {
    }
}

