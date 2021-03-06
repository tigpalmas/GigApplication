package com.example.tiago.myapplication.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.tiago.myapplication.R
import com.example.tiago.myapplication.adapters.ViewPagerCasaDetalheAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_intro.*
import kotlinx.android.synthetic.main.activity_scrolling.*
import kotlinx.android.synthetic.main.fragment_user_perfil.*


/**
 * A simple [Fragment] subclass.
 */
class UserPerfilFragment : Fragment() {


    public override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                                     savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater!!.inflate(R.layout.fragment_user_perfil, container, false)

        return view;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        var viewPagerCasaDetalheAdapter = ViewPagerCasaDetalheAdapter(childFragmentManager);
        viewPagerCasaDetalheAdapter.addFragments(BlankFragment(), "Atividades");
        viewPagerCasaDetalheAdapter.addFragments(UserFavoritsFragment(), "Casas");
        viewPagerCasaDetalheAdapter.addFragments(BlankFragment(), "Eventos");




        viewPager?.offscreenPageLimit = 3
        viewPager?.setAdapter(viewPagerCasaDetalheAdapter);
        tabs.setupWithViewPager(viewPager);

    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title = "Perfil"

    }

}// Required empty public constructor
