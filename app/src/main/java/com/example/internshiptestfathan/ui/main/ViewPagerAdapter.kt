package com.example.internshiptestfathan.ui.main

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
import com.example.internshiptestfathan.ui.Detail.DetailActivity

class ViewPagerAdapter(var activity: Activity, var data : List<String>, var content : Content) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val layoutInflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.item_image, container, false)
        val imageItem = view.findViewById(R.id.iv_img) as ImageView

        imageItem.setOnClickListener {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra("item", content)
            (activity).startActivity(intent)

        }

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
        return 0.9f
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }
}