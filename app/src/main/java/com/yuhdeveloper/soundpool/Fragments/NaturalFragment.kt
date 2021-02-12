package com.yuhdeveloper.soundpool.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yuhdeveloper.soundpool.Adapters.RecContentAdapter
import com.yuhdeveloper.soundpool.Pojos.Categories
import com.yuhdeveloper.soundpool.Pojos.Contents
import com.yuhdeveloper.soundpool.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class NaturalFragment : Fragment() {


    public var content_recyclerView: RecyclerView? = null

  public  var contents: ArrayList<Contents> = ArrayList<Contents>()
    lateinit var tagname: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_natural, container, false)
        initStep(v)
        return v
    }


//    fun natureContentFill(contents: ArrayList<Contents>) {
//        contents.add(Contents(0, "Baykuş", R.drawable.baykus, Categories.doğa.toString(),R.raw.animal_squal))
//        contents.add(Contents(1, "Cırcır Böceği", R.drawable.circirbocegi, Categories.doğa.toString(),R.raw.cat_purr))
//        contents.add(Contents(2, "Kamp Ateşi", R.drawable.kampatesi, Categories.doğa.toString(),R.raw.anime_growl))
//        contents.add(Contents(3, "Kurbağa", R.drawable.kurbaga, Categories.doğa.toString(),R.raw.animal_squal))
//        contents.add(Contents(4, "Yağmur", R.drawable.yagmur, Categories.doğa.toString(),R.raw.animal_squal))
//        contents.add(Contents(5, "Gökgürültüsü", R.drawable.gokgurultusu, Categories.doğa.toString(),R.raw.animal_squal))
//        contents.add(Contents(6, "Kuşlar", R.drawable.kussesi, Categories.doğa.toString(),R.raw.animal_squal))
//        contents.add(Contents(7, "Çadırda Yağmur", R.drawable.cadiryagmur, Categories.doğa.toString(),R.raw.animal_squal))
//        contents.add(Contents(8, "Şelale", R.drawable.selale, Categories.doğa.toString(),R.raw.animal_squal))
//        contents.add(Contents(9, "Kurt", R.drawable.kurt, Categories.doğa.toString(),R.raw.animal_squal))
//        contents.add(Contents(10, "Yılan", R.drawable.yilan, Categories.doğa.toString(),R.raw.animal_squal))
//        contents.add(Contents(11, "Yaprak", R.drawable.agacsesi, Categories.doğa.toString(),R.raw.animal_squal))
//    }

    private fun initStep(v: View) {
        content_recyclerView = v.findViewById(R.id.fragmentContent_recleryview)
        content_recyclerView!!.layoutManager = GridLayoutManager(context!!, 3)

//        if(tagname!=null){
//
//        if (tagname.equals(Categories.doğa.toString())) {
//            natureContentFill(contents)
//        } else if (tagname.equals(Categories.ev.toString())) {
//        } else if (tagname.equals(Categories.sualtı.toString())) {
//        } else if (tagname.equals(Categories.ensrümental.toString())) {
//        }
//        }



        if (contents.size > 0) {
            content_recyclerView!!.adapter = RecContentAdapter(contents)
        } else {
            Toast.makeText(context, "Yakında !", Toast.LENGTH_LONG).show()
        }


    }


}
