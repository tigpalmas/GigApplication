package com.example.tiago.myapplication.model

import com.example.tiago.myapplication.domain.Establishment
import com.example.tiago.myapplication.domain.Event

/**
 * Created by tiago on 09/12/2017.
 */
interface MVP {
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
}