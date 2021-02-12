package com.yuhdeveloper.soundpool.Activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.yuhdeveloper.soundpool.MainActivity
import com.yuhdeveloper.soundpool.R

class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        var intent: Intent = Intent(this, MainActivity::class.java)

                val time = object:CountDownTimer(2000,1000){
                    override fun onFinish() {
                        startActivity(intent)
                        finish()
                    }

                    override fun onTick(p0: Long) {
                    }

                }
        time.start()
    }
}
