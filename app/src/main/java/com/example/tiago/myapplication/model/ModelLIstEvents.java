package com.example.tiago.myapplication.model;

import com.example.tiago.myapplication.domain.Establishment;
import com.example.tiago.myapplication.domain.Event;
import com.example.tiago.myapplication.network.GigProvider;
import com.example.tiago.myapplication.network.GigService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tiago on 17/12/2017.
 */

public class ModelLIstEvents implements MVP.ModelListEvents {
    private GigService service;
    private MVP.PresenterListEvents presenter;

    public ModelLIstEvents(MVP.PresenterListEvents presenter) {
        GigProvider provider = new GigProvider();
        service = provider.getmTService();
        this.presenter = presenter;
    }

    @Override
    public void retrieveEvents(String id) {
        getEvensFromServer(id);
    }

    private void getEvensFromServer(String id) {
        Call<List<Event>> call = service.getEvents(id);
       call.enqueue(new Callback<List<Event>>() {
           @Override
           public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if(response.isSuccessful()){
                    List<Event> events = response.body();
                    if(events.size()>0){

                        presenter.updateList(events);
                        presenter.showLoadProgresss(false, "");
                    }else{
                        presenter.showLoadProgresss(false, "Sem eventos cadastrados no momento");
                    }
                }
           }

           @Override
           public void onFailure(Call<List<Event>> call, Throwable t) {

           }
       });
    }
}
