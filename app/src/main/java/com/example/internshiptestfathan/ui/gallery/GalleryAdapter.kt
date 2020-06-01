package com.example.internshiptestfathan.ui.gallery

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.internshiptestfathan.R
import com.example.internshiptestfathan.model.Gallery
import com.example.internshiptestfathan.ui.Detail.DetailActivity
import kotlinx.android.synthetic.main.gallery_item.view.*

class GalleryAdapter(var activity : Activity, var galleries : List<Gallery>) :
    RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        var view =  LayoutInflater.from(activity).inflate(
            R.layout.gallery_item,
            parent, false)

        return GalleryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return galleries.size
    }

    class GalleryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image = itemView.iv_gallery

    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {

        Glide.with(activity).load(galleries[position].image).centerCrop().into(holder.image)
        holder.image.setOnClickListener {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra("item",galleries[position])
            (activity).startActivity(intent)
        }

    }
}