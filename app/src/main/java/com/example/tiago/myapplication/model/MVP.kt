package com.example.tiago.myapplication.model

import com.example.tiago.myapplication.domain.Establishment
import com.example.tiago.myapplication.domain.Event
import com.example.tiago.myapplication.domain.News
import com.example.tiago.myapplication.domain.TimelineModel

/**
 * Created by tiago on 09/12/2017.
 */
interface MVP {

    //TIMELINE

    interface ModelListTimeLine{
        fun retriveData();
    }

    interface PresenterListTimeLine{
        fun retriveData();
        fun updateList(objects : List<TimelineModel>);
        fun setView(view: MVP.ViewListTimeLine);
        fun showLoadProgresss(status: Boolean, message: String);
         fun getObjects(): List<TimelineModel>
    }

    interface  ViewListTimeLine{
        fun updateList();
        fun showLoadProgresss(status: Boolean, message: String)
    }


    //LIST ESTABLISHMENT
    interface ModelListEstablishments{
        fun retrieveEstablishments();
    }

    interface PresenterListEstablishments{
        fun retrieveEstablishments();
        fun updateList(establishments : List<Establishment>);
        fun setView(view: MVP.ViewListEstablishment);
        fun showLoadProgresss(status: Boolean, message: String);
        fun buscar(s:String?);
        fun limparBusca();
        fun getEstablishment(): List<Establishment>
    }

    interface  ViewListEstablishment{
        fun updateList();
        fun showLoadProgresss(status: Boolean, message: String)
        fun filterEstablishmenn (establishments : List<Establishment>);
    }


    //List Events
    interface ModelListEvents{
        fun retrieveEvents(id: String);
    }

    interface PresenterListEvents{
        fun retrieveEvents(id: String);
        fun updateList(events : List<Event>);
        fun setView(view: MVP.ViewListEvents);
        fun showLoadProgresss(status: Boolean, message: String);
        fun getEvents(): List<Event>
    }

    interface  ViewListEvents{
        fun updateList();
        fun showLoadProgresss(status: Boolean, message: String)
    }

    //List News
    interface ModelListNews{
        fun retrieveNews(id: String);
    }

    interface PresenterListNews{
        fun retrieveNews(id: String);
        fun updateList(events : List<News>);
        fun setView(view: MVP.ViewListNews);
        fun showLoadProgresss(status: Boolean, message: String);
        fun getNews(): List<News>
    }

    interface  ViewListNews{
        fun updateList();
        fun showLoadProgresss(status: Boolean, message: String)
    }
}