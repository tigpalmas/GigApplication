package com.example.tiago.myapplication.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.tiago.myapplication.R
import com.example.tiago.myapplication.domain.Event
import com.example.tiago.myapplication.utils.Util
import com.squareup.picasso.Picasso

/**
 * Created by tiago on 17/12/2017.
 */
class BonusAdapter( ctx: Context): RecyclerView.Adapter<BonusAdapter.ViewHolder>() {

    var context = ctx;


    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {


    }

    override fun getItemCount(): Int {
      return 3
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return BonusAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_bonus, parent, false))
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {


    }
}