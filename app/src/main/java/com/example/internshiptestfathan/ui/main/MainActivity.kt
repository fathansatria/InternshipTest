package com.example.internshiptestfathan.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.internshiptestfathan.R
import com.example.internshiptestfathan.model.ProfileResponse
import com.example.internshiptestfathan.ui.gallery.GalleryFragment
import com.example.internshiptestfathan.ui.main.MainFragment
import com.example.internshiptestfathan.ui.profile.ProfileFragment
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    var currentFragment : Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            switchFragment(MainFragment.newInstance())
        }

        setSupportActionBar(toolbar)
        supportActionBar?.show()

        bottom_navigation.offsetTopAndBottom(0)
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.menu_list ->{
                    currentFragment = MainFragment.newInstance()
                    switchFragment(currentFragment!!)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_gallery ->{
                    currentFragment = GalleryFragment.newInstance()
                    switchFragment(currentFragment!!)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_profile ->{
                    currentFragment = ProfileFragment.newInstance()
                    switchFragment(currentFragment!!)
                    return@setOnNavigationItemSelectedListener true
                }

            }

            return@setOnNavigationItemSelectedListener false

        }
    }

    private fun switchFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    override fun onBackPressed() {

        when(currentFragment){
            is MainFragment->{
                super.onBackPressed()
            }
            is GalleryFragment->{
                currentFragment = MainFragment.newInstance()
                switchFragment(currentFragment!!)
            }
            is ProfileFragment->{
                currentFragment = MainFragment.newInstance()
                switchFragment(currentFragment!!)
            }
        }

    }
}