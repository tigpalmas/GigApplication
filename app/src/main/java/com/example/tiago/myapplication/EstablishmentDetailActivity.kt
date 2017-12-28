package com.example.tiago.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.example.tiago.myapplication.adapters.ViewPagerCasaDetalheAdapter
import com.example.tiago.myapplication.domain.Establishment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_establishment_detail.*
import kotlinx.android.synthetic.main.content_base_establishment.*
import android.view.MenuItem
import com.example.tiago.myapplication.fragments.*


class EstablishmentDetailActivity : AppCompatActivity() {
     var meuMenu: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_establishment_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        val establishment: Establishment? = intent?.getSerializableExtra("hello") as? Establishment;

        Picasso.with(this).load(establishment?.imgLogo).into(img_event_detail);
        txt_perfil_title?.text = establishment?.personalDataId?.name

        var viewPagerCasaDetalheAdapter = ViewPagerCasaDetalheAdapter(supportFragmentManager);

        if(establishment!=null){
            val perfilFragment = PerfilEstablishmentFragment.novaInstancia(establishment)
            val fragmentEvents = EventsFragment.novaInstancia(establishment)
            val fragmentNews = NewsFragment.novaInstancia(establishment)
            val fragmentMap = MapFragment.novaInstancia(establishment)
            viewPagerCasaDetalheAdapter.addFragments(perfilFragment, "Sobre");
            viewPagerCasaDetalheAdapter.addFragments(fragmentMap, "Localização");
            viewPagerCasaDetalheAdapter.addFragments(fragmentEvents, "Programação")
            viewPagerCasaDetalheAdapter.addFragments(BonusFragment(), "Bônus")
            viewPagerCasaDetalheAdapter.addFragments(fragmentNews, "Noticías")
        }

        viewPager?.offscreenPageLimit = 3
        viewPager?.setAdapter(viewPagerCasaDetalheAdapter);
        tabs?.setupWithViewPager(viewPager);
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
