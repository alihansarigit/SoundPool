package com.yuhdeveloper.soundpool.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yuhdeveloper.soundpool.Pojos.Contents
import com.yuhdeveloper.soundpool.ViewHolders.RecContentViewHolders
import android.widget.LinearLayout
import com.yuhdeveloper.soundpool.R


class RecContentAdapter(_contents: ArrayList<Contents>) : RecyclerView.Adapter<RecContentViewHolders>() {
    var contents: ArrayList<Contents>

    init {
        contents = _contents
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecContentViewHolders {
        var v: View = LayoutInflater.from(parent.context!!).inflate(R.layout.custom_rec_contentitems, parent, false)
        return RecContentViewHolders(v)
    }

    override fun getItemCount(): Int {
        return contents.size
    }

    override fun onBindViewHolder(holder: RecContentViewHolders, position: Int) {
        if (position < 3) {
            var params = holder.content_img!!.layoutParams as LinearLayout.LayoutParams
            params.setMargins(10,60,10,10)
            holder.content_img!!.layoutParams = params
        }

        holder.set(contents.get(position))
    }
}