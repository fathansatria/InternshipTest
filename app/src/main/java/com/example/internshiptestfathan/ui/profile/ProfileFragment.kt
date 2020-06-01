package com.example.internshiptestfathan.ui.profile

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.internshiptestfathan.R
import com.example.internshiptestfathan.ui.main.MainActivity
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.main_activity.toolbar
import kotlinx.android.synthetic.main.main_activity.tv_toolbar
import kotlinx.android.synthetic.main.profile_fragment.*

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)

        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity)?.toolbar?.setNavigationOnClickListener {
            (activity as MainActivity).bottom_navigation.selectedItemId = R.id.menu_list
        }
        activity?.tv_toolbar?.text = "Profile"


        viewModel.setProfileData()
        viewModel.getProfileData().observe(this, Observer {

            if(it != null && activity != null){

                tv_nickname.text = it.username
                tv_fullname.text = it.fullname
                tv_email.text = it.email
                tv_phone.text = it.phone

                Glide.with(activity!!.applicationContext).load(it.avatar).
                centerCrop().into(ci_avatar)

            }

        })

    }

}