package com.example.internshiptestfathan.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.internshiptestfathan.R
import com.example.internshiptestfathan.ui.main.MainActivity
import kotlinx.android.synthetic.main.gallery_fragment.*
import kotlinx.android.synthetic.main.main_activity.*

class GalleryFragment : Fragment() {


    companion object {
        fun newInstance() =
            GalleryFragment()
    }

    private lateinit var viewModel: GalleryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.gallery_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rv_gallery.layoutManager = GridLayoutManager(activity?.applicationContext, 3)

        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity)?.toolbar?.setNavigationOnClickListener {
            (activity as MainActivity).bottom_navigation.selectedItemId = R.id.menu_list
        }
        activity?.tv_toolbar?.text = "Gallery"

        showLoading(true)

        viewModel = ViewModelProviders.of(this).get(GalleryViewModel::class.java)
        viewModel.setGalleryData()
        viewModel.getAllGalleryData().observe(this, Observer {

            if(it != null){
                rv_gallery.adapter = GalleryAdapter(activity!!, it)
            }

            showLoading(false)
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            detail_progress_bar.visibility = View.VISIBLE
        } else {
            detail_progress_bar.visibility = View.GONE
        }
    }



}