package com.example.tiago.myapplication

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.example.tiago.myapplication.adapters.ViewPagerCasaDetalheAdapter
import com.example.tiago.myapplication.domain.Establishment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_establishment_detail.*
import kotlinx.android.synthetic.main.content_base_establishment.*
import android.view.MenuItem
import com.example.tiago.myapplication.adapters.ViewPagerCoversAdapter
import com.example.tiago.myapplication.domain.Cover
import com.example.tiago.myapplication.fragments.*


class EstablishmentDetailActivity : AppCompatActivity() {
     private var meuMenu: MenuItem? = null
    private var viewPagerCoversAdapter: ViewPagerCoversAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_establishment_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        val establishment: Establishment? = intent?.getSerializableExtra("hello") as? Establishment;

        Picasso.with(this).load(establishment?.imgLogo).into(img_base_cover);
        txt_perfil_title?.text = establishment?.personalDataId?.name
      /*  if(establishment?.coverId!=null){
            loadCovers(establishment.coverId)

        }*/





        var viewPagerCasaDetalheAdapter = ViewPagerCasaDetalheAdapter(supportFragmentManager);

        if(establishment!=null){
            val perfilFragment = PerfilEstablishmentFragment.novaInstancia(establishment)
            val fragmentEvents = EventsFragment.novaInstancia(establishment)
            val fragmentBonus = BonusFragment.novaInstancia(establishment)
            val fragmentNews = NewsFragment.novaInstancia(establishment)
            val fragmentMap = MapFragment.novaInstancia(establishment)
            viewPagerCasaDetalheAdapter.addFragments(perfilFragment, "Sobre");
            viewPagerCasaDetalheAdapter.addFragments(fragmentMap, "Localização");
            viewPagerCasaDetalheAdapter.addFragments(fragmentEvents, "Programação")
            viewPagerCasaDetalheAdapter.addFragments(fragmentBonus, "Bônus")
            viewPagerCasaDetalheAdapter.addFragments(fragmentNews, "Noticías")
        }

        viewPager?.offscreenPageLimit = 3
        viewPager?.setAdapter(viewPagerCasaDetalheAdapter);
        tabs?.setupWithViewPager(viewPager);
    }


    fun loadCovers(covers: List<Cover>) {
        viewPagerCoversAdapter = ViewPagerCoversAdapter(supportFragmentManager)
        for (cover in covers) {
            val coverFragment = CoverFragment.novaInstancia(cover.imageUrl)
            viewPagerCoversAdapter!!.addFragments(coverFragment, "")
        }
        vp_covers?.adapter = viewPagerCoversAdapter
        tab_dots?.setupWithViewPager(vp_covers)
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
