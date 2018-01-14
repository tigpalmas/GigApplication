package com.example.tiago.myapplication.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.tiago.myapplication.R

import com.example.tiago.myapplication.domain.Establishment
import kotlinx.android.synthetic.main.fragment_perfil_establishment.*


/**
 * A simple [Fragment] subclass.
 */
class PerfilEstablishmentFragment : Fragment() {

    private var mEstablishment: Establishment? = null;


    companion object {
        val EXTRA_ESTABLISMENT = "extra_establishment"

        fun novaInstancia(establishment: Establishment): PerfilEstablishmentFragment {
            val parametros = Bundle()
            parametros.putSerializable(EXTRA_ESTABLISMENT, establishment)
            val fragment = PerfilEstablishmentFragment()
            fragment.arguments = parametros
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        retainInstance = true
        mEstablishment = arguments?.getSerializable(EXTRA_ESTABLISMENT) as? Establishment



    }



    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater!!.inflate(R.layout.fragment_perfil_establishment, container, false)
        return view;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var tags: String = ""
       /* for(i in mEstablishment?.tagId!!){
            tags = "${tags+i.name} "
        }*/

        txt_tags?.text = tags

        txt_perfilname?.text = mEstablishment?.personalDataId?.name
        txt_perfil_adress?.text = mEstablishment?.addressId?.street
        txt_description?.text = mEstablishment?.description
        //txt_capacity?.text = mEstablishment?.capacity
    }

}// Required empty public constructor
