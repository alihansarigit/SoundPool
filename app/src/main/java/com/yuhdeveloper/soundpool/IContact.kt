package com.yuhdeveloper.soundpool

import com.yuhdeveloper.soundpool.Pojos.Contents

interface IContact {


    fun isActivated(click:Boolean,content: Contents)

    fun changeVolume(_volume: Float, _contents:Contents)
}