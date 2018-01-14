package com.example.tiago.myapplication.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.example.tiago.myapplication.EventDetailActivity
import com.example.tiago.myapplication.R
import com.example.tiago.myapplication.domain.Establishment
import com.example.tiago.myapplication.domain.Event
import com.example.tiago.myapplication.domain.TimelineModel
import com.example.tiago.myapplication.utils.Util
import com.squareup.picasso.Picasso

/**
 * Created by tiago on 17/12/2017.
 */
class TimeLineAdapter(items: List<TimelineModel>, ctx: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var list = items;
    var context = ctx;
    val EVENT = 0
    val NEWS = 1
    val BONUS = 2


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        var item = list.get(position);
        when (holder?.itemViewType) {
            EVENT -> {
                val vh1 = holder as ViewHolderEvent
                vh1.bind(item, context);
            }
            NEWS -> {
                val vh2 = holder as ViewHolderNews
                vh2.bind(item, context)
            }
            BONUS -> {
                val vh3 = holder as ViewHolderBonus
                vh3.bind(item, context)
            }
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        val item = list.get(position);
        if (item.eventId != null) {
            return EVENT
        } else if (item.bonusId != null) {
            return BONUS
        } else if (item.newsId != null) {
            return NEWS
        }
        return -1
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder
        val inflater = LayoutInflater.from(parent?.getContext())

        when (viewType) {
            EVENT -> {
                val v1 = inflater.inflate(R.layout.item_event, parent, false)
                viewHolder = ViewHolderEvent(v1)
            }
            NEWS -> {
                val v2 = inflater.inflate(R.layout.item_news, parent, false)
                viewHolder = ViewHolderNews(v2)
            }
            else -> {
                val v3 = inflater.inflate(R.layout.item_bonus, parent, false)
                viewHolder = ViewHolderBonus(v3)
            }
        }
        return viewHolder


    }


    class ViewHolderEvent(itemView: View?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var ctx: Context? = null
        var item: TimelineModel? = null
        var event: Event? = null
        var establishment: Establishment? = null;
        val imgEvent = itemView?.findViewById<ImageView>(R.id.iv_bonus);
        val imgEstablishment = itemView?.findViewById<ImageView>(R.id.img_establishment);
        val txtEventName = itemView?.findViewById<TextView>(R.id.txt_event_name);
        val txtEventLocal = itemView?.findViewById<TextView>(R.id.txt_event_local);
        val txtEventCity = itemView?.findViewById<TextView>(R.id.txt_event_city);
        val txtMonth = itemView?.findViewById<TextView>(R.id.txt_month);
        val txtDay = itemView?.findViewById<TextView>(R.id.txt_day);

        init {
            itemView?.setOnClickListener(this)
        }

        fun bind(item: TimelineModel, ctx: Context) {
            this.item = item;
            this.ctx = ctx;
            event = item?.eventId;
            establishment = item?.establishmentId;

            txtMonth?.setText(Util.obterMonth(item?.eventId?.beginDate).toUpperCase())
            txtDay?.setText(Util.obterDay(item?.eventId?.beginDate))
            txtEventName?.text = event?.name
            txtEventLocal?.text = establishment?.personalDataId?.name
            txtEventCity?.text =  establishment?.addressId?.city
            Picasso.with(ctx).load(event?.imgUrl).into(imgEvent)
            Picasso.with(ctx).load(establishment?.imgLogo).into(imgEstablishment)
        }

        override fun onClick(p0: View?) {
            val intent = Intent(ctx, EventDetailActivity::class.java)
            intent.putExtra("timelineExtra", item);
            ctx?.startActivity(intent)
        }
    }

    class ViewHolderNews(itemView: View?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        lateinit var ctx: Context

        init {

        }

        fun bind(item: TimelineModel, ctx: Context) {
            this.ctx = ctx;


        }

        override fun onClick(p0: View?) {
            /*  val fragmentTransaction = (ctx as FragmentActivity).supportFragmentManager.beginTransaction()
              val fragment = EventDetailFragment.novaInstancia(event);
              fragmentTransaction?.add(R.id.container, fragment, "event")
              fragmentTransaction?.addToBackStack(null)
              fragmentTransaction?.commit()*/

            Util.showToast(ctx, "Ainda não implementado")
        }
    }

    class ViewHolderBonus(itemView: View?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        lateinit var ctx: Context

        init {

        }

        fun bind(item: TimelineModel, ctx: Context) {
            this.ctx = ctx;


        }

        override fun onClick(p0: View?) {
            /*  val fragmentTransaction = (ctx as FragmentActivity).supportFragmentManager.beginTransaction()
              val fragment = EventDetailFragment.novaInstancia(event);
              fragmentTransaction?.add(R.id.container, fragment, "event")
              fragmentTransaction?.addToBackStack(null)
              fragmentTransaction?.commit()*/

            Util.showToast(ctx, "Ainda não implementado")
        }
    }
}