package com.yuhdeveloper.soundpool.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.yuhdeveloper.soundpool.R

class ViewPagerAdapter(
    manager: FragmentManager,
    _lstTitle: ArrayList<String>,
    _lstFragment: ArrayList<Fragment>,
    _lstImage: ArrayList<Int>) :
    FragmentPagerAdapter(manager) {

    var lstFragment = _lstFragment
    var lstTitle = _lstTitle
    var lstImage = _lstImage
    var mana=manager

    override fun getItem(position: Int): Fragment {
        return lstFragment.get(position)
    }

    override fun getCount(): Int {
        return lstFragment!!.size

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return lstTitle.get(position)
    }

    fun getTabView(position: Int,context: Context): View {
        // Given you have a custom layout in `res/layout/custom_tab.xml` with a TextView and ImageView
        var v = LayoutInflater.from(context).inflate(R.layout.custom_category_tabitem, null,false)
        var tv: TextView = v.findViewById(R.id.custom_tabItem_txtName)
        tv.setText(lstTitle.get(position))
        var img: ImageView = v.findViewById(R.id.custom_tabItem_imgPic)
        img.setImageResource(lstImage.get(position))
        return v
    }


}