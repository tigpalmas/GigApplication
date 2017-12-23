package com.example.tiago.myapplication

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.example.tiago.myapplication.adapters.ViewPagerCasaDetalheAdapter
import com.example.tiago.myapplication.domain.Establishment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_establishment_detail.*
import kotlinx.android.synthetic.main.content_base_establishment.*
import android.R.attr.name
import android.support.design.widget.AppBarLayout
import android.util.Log
import android.view.MenuItem
import com.example.tiago.myapplication.fragments.*
import com.example.tiago.myapplication.utils.AppBarStateChangeListener



class EstablishmentDetailActivity : AppCompatActivity() {
     var meuMenu: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_establishment_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        val establishment: Establishment = intent.getSerializableExtra("hello") as Establishment;

     /*   app_bar.addOnOffsetChangedListener(object : AppBarStateChangeListener() {
            override fun onStateChanged(appBarLayout: AppBarLayout, state: AppBarStateChangeListener.State) {
                if(state.name=="COLLAPSED"){
                    supportActionBar?.setTitle("")
                    meuMenu?.isVisible = false
                }else if(state.name=="EXPANDED"){
                    supportActionBar?.setTitle(establishment.personalDataId.name)
                    meuMenu?.isVisible = true
                }
            }
        })*/



        Picasso.with(this).load(establishment.imgLogo).into(img_event_detail);
      //  supportActionBar?.setTitle(establishment.personalDataId.name)

        var viewPagerCasaDetalheAdapter = ViewPagerCasaDetalheAdapter(supportFragmentManager);
        viewPagerCasaDetalheAdapter.addFragments(PerfilEstablishmentFragment(), "Sobre");
        viewPagerCasaDetalheAdapter.addFragments(MapFragment(), "Localização");

        if(establishment!=null){
            val fragment = EventsFragment.novaInstancia(establishment!!)
            viewPagerCasaDetalheAdapter.addFragments(fragment, "Programação")
            viewPagerCasaDetalheAdapter.addFragments(BonusFragment(), "Bônus")
            viewPagerCasaDetalheAdapter.addFragments(NewFragment(), "Noticías")
        }

        viewPager?.offscreenPageLimit = 3
        viewPager?.setAdapter(viewPagerCasaDetalheAdapter);
        tabs.setupWithViewPager(viewPager);
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (menu != null) {
            meuMenu = menu?.findItem(R.id.action_favorit)
        };
        meuMenu?.isVisible = true
        return true;
    }

}
