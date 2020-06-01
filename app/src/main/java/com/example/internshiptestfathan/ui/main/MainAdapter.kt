package com.example.internshiptestfathan.ui.main

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.bumptech.glide.Glide
import com.example.internshiptestfathan.R
import com.example.internshiptestfathan.model.Content
import com.example.internshiptestfathan.model.Header
import kotlinx.android.synthetic.main.view_holder_header.view.*
import kotlinx.android.synthetic.main.view_holder_multiple.view.*
import kotlinx.android.synthetic.main.view_holder_multiple.view.tv_subtitle
import kotlinx.android.synthetic.main.view_holder_multiple.view.tv_title
import kotlinx.android.synthetic.main.view_holder_single.view.*

class MainAdapter(var activity: Activity?, var data : ArrayList<Any>) : RecyclerView.Adapter<ViewHolder>(){

    private val HEADER = 1
    private val SINGLE = 2
    private val MULTIPLE = 3

    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var view =  LayoutInflater.from(activity).inflate(R.layout.view_holder_header,
            parent, false)

        when(viewType){
            HEADER -> {
                view =  LayoutInflater.from(activity).inflate(R.layout.view_holder_header,
                    parent, false)
                return HeaderViewHolder(view)
            }
            SINGLE -> {
                view =  LayoutInflater.from(activity).inflate(R.layout.view_holder_single,
                    parent, false)
                return SingleViewHolder(view)
            }
            MULTIPLE -> {
                view =  LayoutInflater.from(activity).inflate(R.layout.view_holder_multiple,
                    parent, false)
                return MultipleViewHolder(view)
            }
        }

        return HeaderViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        when(holder){
            is HeaderViewHolder -> {
                holder.header.text = (data[position] as Header).title
                holder.subheader.text = (data[position] as Header).subtitle
            }
            is SingleViewHolder -> {
                holder.title.text = (data[position] as Content).title
                holder.content.text = (data[position] as Content).content
                Glide.with(activity!!).load((data[position] as Content).image).centerCrop().into(holder.image)
            }
            is MultipleViewHolder -> {

                holder.title.text = (data[position] as Content).title
                holder.content.text = (data[position] as Content).content

                if((data[position] as Content).media != null){
                    holder.viewPager.adapter = ViewPagerAdapter(activity!!, (data[position] as Content).media!!, (data[position] as Content))

                }
            }
        }

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(data[holder.adapterPosition])
        }

    }

    override fun getItemViewType(position: Int): Int {

        var type = 0
        when(data[position]){
            is Header -> type = HEADER
            is Content -> {
                return if((data[position] as Content).type == "image"){
                    SINGLE
                } else{
                    MULTIPLE
                }
            }
        }

        return type
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    internal class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val header = itemView.tv_header
        val subheader = itemView.tv_subheader

    }

    internal class MultipleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.tv_title
        val content = itemView.tv_subtitle
        val viewPager = itemView.vp_multiple

    }

    internal class SingleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.tv_title
        val content = itemView.tv_subtitle_single
        val image = itemView.iv_single_image

    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Any)
    }


}