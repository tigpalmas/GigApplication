package com.example.tiago.myapplication.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.tiago.myapplication.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_establishment_detail.*
import kotlinx.android.synthetic.main.fragment_cover.*


/**
 * A simple [Fragment] subclass.
 */
class CoverFragment : Fragment() {

    private var mCover: String? = null

    companion object {
        val EXTRA_COVER = "extra_establishment"

        fun novaInstancia(coverUrl: String): CoverFragment {
            val parametros = Bundle()
            parametros.putString(EXTRA_COVER, coverUrl)
            val fragment = CoverFragment()
            fragment.arguments = parametros
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        retainInstance = true
        mCover = arguments?.getString(EXTRA_COVER)
    }



    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater!!.inflate(R.layout.fragment_cover, container, false)

        return view;
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        Picasso.with(activity).load(mCover).into(img_cover, object : Callback {
            override fun onSuccess() {
                pb_cover.setVisibility(View.GONE)
            }

            override fun onError() {
                pb_cover.setVisibility(View.GONE)
            }
        })
    }

}// Required empty public constructor
