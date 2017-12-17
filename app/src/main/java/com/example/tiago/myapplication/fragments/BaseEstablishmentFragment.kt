package com.example.tiago.myapplication.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.tiago.myapplication.R
import com.example.tiago.myapplication.adapters.ViewPagerCasaDetalheAdapter
import com.example.tiago.myapplication.domain.Establishment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_base_establishment.*
import kotlinx.android.synthetic.main.fragment_blank_fragment2.*


/**
 * A simple [Fragment] subclass.
 */
class BaseEstablishmentFragment : Fragment() {
    private var mEstablishment: Establishment? = null;


    companion object {
        val EXTRA_ESTABLISHMENT = "extra_establishment"

        fun novaInstancia(establishment: Establishment): BaseEstablishmentFragment {
            val parametros = Bundle()
            parametros.putSerializable(EXTRA_ESTABLISHMENT, establishment)
            val fragment = BaseEstablishmentFragment()
            fragment.arguments = parametros
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        setHasOptionsMenu(true)
        if (arguments != null) {
            if (arguments.getSerializable(EXTRA_ESTABLISHMENT) != null) {
                mEstablishment = arguments.getSerializable(EXTRA_ESTABLISHMENT) as Establishment
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
         val view = inflater!!.inflate(R.layout.fragment_blank_fragment2, container, false)
        return view;
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Picasso.with(activity).load(mEstablishment?.imgLogo).into(iv_prom)
        var viewPagerCasaDetalheAdapter = ViewPagerCasaDetalheAdapter(childFragmentManager);
        viewPagerCasaDetalheAdapter.addFragments(PerfilEstablishmentFragment(), "Perfil");
        viewPagerCasaDetalheAdapter.addFragments(MapFragment(), "Localização");

        if(mEstablishment!=null){
            val fragment = EventsFragment.novaInstancia(mEstablishment!!)
            viewPagerCasaDetalheAdapter.addFragments(fragment, "Programação")
        }


        viewPager.setAdapter(viewPagerCasaDetalheAdapter);
        tabs.setupWithViewPager(viewPager);
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.title = mEstablishment!!.personalDataId.name
    }



}// Required empty public constructor
