package com.example.tiago.myapplication.model;

import com.example.tiago.myapplication.domain.Event;
import com.example.tiago.myapplication.domain.TimelineModel;
import com.example.tiago.myapplication.network.GigProvider;
import com.example.tiago.myapplication.network.GigService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tiago on 23/12/2017.
 */

public class ModelTimeline implements MVP.ModelListTimeLine {
    private GigService service;
    private MVP.PresenterListTimeLine presenter;

    public ModelTimeline(MVP.PresenterListTimeLine presenter) {
        GigProvider provider = new GigProvider();
        service = provider.getmTService();
        this.presenter = presenter;
    }

    @Override
    public void retriveData() {
        getObjectsFromServer();
    }

    private void getObjectsFromServer(){
        Call<List<TimelineModel>> call = service.getObjects();
        call.enqueue(new Callback<List<TimelineModel>>() {
            @Override
            public void onResponse(Call<List<TimelineModel>> call, Response<List<TimelineModel>> response) {
                if(response.isSuccessful()){
                    List<TimelineModel> objects = response.body();
                    if(objects.size()>0){
                        presenter.updateList(objects);
                        presenter.showLoadProgresss(false, "");
                    }else{
                        presenter.showLoadProgresss(false, "Sem eventos cadastrados no momento");
                    }
                }else{
                    presenter.showLoadProgresss(false, "error");
                }
            }

            @Override
            public void onFailure(Call<List<TimelineModel>> call, Throwable t) {
                presenter.showLoadProgresss(false, t.getMessage());
            }
        });
    }
}
