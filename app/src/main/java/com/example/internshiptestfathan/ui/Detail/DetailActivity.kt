package com.example.internshiptestfathan.ui.Detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.internshiptestfathan.R
import com.example.internshiptestfathan.model.Content
import com.example.internshiptestfathan.model.Gallery
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.toolbar

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setSupportActionBar(toolbar)
        supportActionBar?.show()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar?.setNavigationOnClickListener {
            onBackPressed()
        }


        val data = intent.getParcelableExtra("item") as Any?
        if(data != null && data is Content){

            if(data.type == "image"){

                iv_detail.visibility = View.VISIBLE
                fl_layout.visibility = View.GONE
                Glide.with(this).load(data.image).into(iv_detail)
                tv_toolbar.text = "Detail Single"
            }
            else{

                fl_layout.visibility = View.VISIBLE
                iv_detail.visibility = View.GONE
                vp_detail.adapter = ViewPagerDetailAdapter(this, data.media!!, data)
                dots_indicator.setViewPager(vp_detail)
                tv_toolbar.text = "Detail Multiple"

            }

            tv_detail_title.text = data.title
            tv_detail_subtitle.text =data.content
        }
        else if(data != null && data is Gallery){

            iv_detail.visibility = View.VISIBLE
            fl_layout.visibility = View.GONE
            tv_detail_subtitle.text =data.caption
            tv_toolbar.text = "Detail Gallery"

            Glide.with(this).load(data.image).into(iv_detail)


        }


    }
}