package com.example.internshiptestfathan.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.internshiptestfathan.R
import com.example.internshiptestfathan.model.Content
import com.example.internshiptestfathan.ui.Detail.DetailActivity
import kotlinx.android.synthetic.main.activity_detail.tv_toolbar
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    var allData = ArrayList<Any>()

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        showLoading(true)
        rv_main.layoutManager = LinearLayoutManager(activity)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        activity?.tv_toolbar?.text = "list"

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.setData()
        viewModel.getAllData().observe(this, Observer {

            if(it != null){

                allData.add(it.header!!)
                allData.addAll(it.content!!)

                val mainAdapter = MainAdapter(activity, allData)
                rv_main.adapter = mainAdapter

                mainAdapter.setOnItemClickCallback(object : MainAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: Any) {
                        if(data is Content){
                            val intent = Intent(activity, DetailActivity::class.java)
                            intent.putExtra("item", data)
                            startActivity(intent)
                        }

                    }
                })

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