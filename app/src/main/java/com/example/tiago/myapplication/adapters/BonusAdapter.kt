package com.example.tiago.myapplication.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.tiago.myapplication.R
import com.example.tiago.myapplication.domain.Bonus
import com.example.tiago.myapplication.domain.Event
import com.example.tiago.myapplication.utils.Util
import com.squareup.picasso.Picasso

/**
 * Created by tiago on 17/12/2017.
 */
class BonusAdapter(items: List<Bonus>, ctx: Context): RecyclerView.Adapter<BonusAdapter.ViewHolder>() {
    var list = items;
    var context = ctx;


    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        var item = list.get(position);
        holder?.bind(item, context);
    }

    override fun getItemCount(): Int {
      return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return BonusAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_bonus, parent, false))
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val imgBonus = itemView?.findViewById<ImageView>(R.id.iv_bonus);
        lateinit var ctx : Context
        lateinit var bonus: Bonus;

        fun bind(item: Bonus, ctx: Context){
            this.ctx = ctx;
            bonus = item;

            // txtDate?.setText(Util.obterDataPorExtenso(item?.beginDate))

            Picasso.with(ctx).load(item?.eventId?.imgUrl).into(imgBonus)

        }

    }
}