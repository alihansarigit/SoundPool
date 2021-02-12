package com.yuhdeveloper.soundpool.Pojos

import android.media.MediaPlayer

class Contents(_id:Int,_name:String,_img:Int,_category:String,_music:Int) {

    var id:Int=_id
    var name:String=_name
    var img:Int=_img
    var category:String=_category
    var isActive:Boolean =false
    var music:Int=_music
    var streamID: MediaPlayer?=null
    var soundID:Int=-1
    var volume:Int=50


}