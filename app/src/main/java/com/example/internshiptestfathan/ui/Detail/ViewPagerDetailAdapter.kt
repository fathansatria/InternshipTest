package com.example.internshiptestfathan.ui.Detail


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.internshiptestfathan.R
import com.example.internshiptestfathan.model.Content

class ViewPagerDetailAdapter(var activity: Activity, var data : List<String>, var content : Content) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val layoutInflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.item_detail, container, false)
        val imageItem = view.findViewById(R.id.iv_img) as ImageView


        Glide.with(activity)
            .load(data[position])
            .centerCrop()
            .into(imageItem)

        container.addView(view)
        return view
    }
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun getPageWidth(position: Int): Float {
        return 1f
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }
}