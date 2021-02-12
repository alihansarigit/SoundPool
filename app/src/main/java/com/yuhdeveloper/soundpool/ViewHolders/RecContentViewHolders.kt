package com.yuhdeveloper.soundpool.ViewHolders

import android.annotation.SuppressLint
import android.media.SoundPool
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.yuhdeveloper.soundpool.IContact
import com.yuhdeveloper.soundpool.Pojos.Contents
import com.yuhdeveloper.soundpool.R


class RecContentViewHolders(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener,
    SoundPool.OnLoadCompleteListener, SeekBar.OnSeekBarChangeListener {
    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        val volume = (1 - Math.log((100 - p1).toDouble()) / Math.log(100.0)).toFloat()

        contactListener.changeVolume(volume,content)
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
    }

    override fun onLoadComplete(p0: SoundPool?, p1: Int, p2: Int) {
    }


    private val NR_OF_SIMULTANEOUS_SOUNDS = 7
    private val LEFT_VOLUME = 1.0f
    private val RIGHT_VOLUME = 1.0f
    private val NO_LOOP = -1
    private val PRIORITY = 0
    private val NORMAL_PLAY_RATE = 0.99f

    var content_img: ImageView? = null
    var content_txt: TextView? = null
    var id: Int? = null

    var contactListener: IContact
     var content_volume: SeekBar?=null

    lateinit var content: Contents

    init {
        content_img = itemView.findViewById(R.id.custom_rec_content_imgPic)
        content_txt = itemView.findViewById(R.id.custom_rec_content_txtName)

        content_volume = itemView.findViewById(R.id.custom_rec_content_volume)
        content_volume!!.max=100
        content_volume!!.setOnSeekBarChangeListener(this)
        itemView.setOnClickListener(this)
        contactListener = itemView.context as IContact
    }

    fun set(_content: Contents) {
        content = _content
        dataSet()
    }

    private fun dataSet() {
        content_txt?.setText(content.name)
        content_img?.setImageResource(content.img)
        if(content.isActive){
            content_volume?.visibility = View.VISIBLE
            content_volume?.progress=content.volume;
            content_img?.setColorFilter(ContextCompat.getColor(itemView.context,R.color.white),android.graphics.PorterDuff.Mode.SRC_IN)
        }else{
            content_volume?.visibility = View.INVISIBLE
        }
    }

    @SuppressLint("ResourceAsColor")
    override fun onClick(p0: View?) {
        content.isActive = !content.isActive
        contactListener.isActivated(content.isActive,content)

        if (content.isActive) {
            content_volume?.visibility = View.VISIBLE
            content_volume?.progress=content.volume;
            content_img!!.setColorFilter(ContextCompat.getColor(itemView.context, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);

        } else {
            content_volume?.visibility = View.INVISIBLE
            content_img!!.setColorFilter(ContextCompat.getColor(itemView.context, R.color.black), android.graphics.PorterDuff.Mode.SRC_IN);
        }
    }


}
