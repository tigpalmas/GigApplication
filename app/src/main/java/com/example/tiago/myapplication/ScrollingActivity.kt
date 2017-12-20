package com.example.tiago.myapplication

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.example.tiago.myapplication.fragments.ListEstablishmentFragment
import com.example.tiago.myapplication.fragments.TimeLineFragment
import com.example.tiago.myapplication.utils.BottomNavigationViewHelper
import kotlinx.android.synthetic.main.activity_scrolling.*

class ScrollingActivity : AppCompatActivity() {

    val manager = supportFragmentManager

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                val fragment = ListEstablishmentFragment();
                ShowFragmnetON(fragment, "fragment1");
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                val fragment = TimeLineFragment();
                ShowFragmnetON(fragment, "fragment2");
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_Favoritos -> {


                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(toolbar)
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


    fun ShowFragmnetON(fragment: Fragment, tag: String){
        val transaction = manager.beginTransaction().setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out );
        transaction.replace(R.id.container, fragment,tag);
        transaction.commit();
    }
}