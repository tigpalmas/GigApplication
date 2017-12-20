package com.example.tiago.myapplication.adapters

import android.content.Context
import android.content.Intent
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.tiago.myapplication.EstablishmentDetailActivity
import com.example.tiago.myapplication.R
import com.example.tiago.myapplication.domain.Establishment
import com.example.tiago.myapplication.fragments.BaseEstablishmentFragment
import com.squareup.picasso.Picasso

/**
 * Created by tiago on 08/12/2017.
 */
class EstablishmentsAdapter(items: List<Establishment>, ctx: Context): RecyclerView.Adapter<EstablishmentsAdapter.ViewHolder>() {


    var list = items;
    var context = ctx;
    var width: Int;

    init {
        width =  ctx.getResources().getDisplayMetrics().widthPixels;
    }


    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        var item = list.get(position);
        holder?.bind(item, context, width);
    }

    override fun getItemCount(): Int {
       return list.size;
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return   ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        private var ctx: Context? = null
        var ivLogo = itemView.findViewById<ImageView>(R.id.iv_logo);
        lateinit var establishment :Establishment

        init {
           ivLogo.setOnClickListener(this);
        }

        fun bind(item: Establishment, ctx: Context, width: Int){
            Picasso.with(ctx).load(item.imgLogo).resize(width/2, width/2).into(ivLogo)
            establishment = item;
            this.ctx = ctx;
        }

        override fun onClick(p0: View?) {
            val intent = Intent(ctx,EstablishmentDetailActivity::class.java)
            intent.putExtra("hello", establishment);
            ctx?.startActivity(intent)
        }


    }
}