package com.example.tiago.myapplication.model;

import com.example.tiago.myapplication.domain.Establishment;
import com.example.tiago.myapplication.network.GigProvider;
import com.example.tiago.myapplication.network.GigService;
import com.example.tiago.myapplication.presenter.PresenterListEstablishment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tiago on 09/12/2017.
 */

public class ModelListEstablishment implements MVP.ModelListEstablishments {
    private GigService service;
    private MVP.PresenterListEstablishments presenter;


    public ModelListEstablishment(PresenterListEstablishment presenterListEstablishment) {
        GigProvider provider = new GigProvider();
        service = provider.getmTService();
        presenter = presenterListEstablishment;
    }

    @Override
    public void retrieveEstablishments() {
      getEstablishmentFromServer();
    }

    private void getEstablishmentFromServer() {
        Call<List<Establishment>> call = service.getEstablishments();
        call.enqueue(new Callback<List<Establishment>>() {
            @Override
            public void onResponse(Call<List<Establishment>> call, Response<List<Establishment>> response) {
                List<Establishment> establishments = response.body();
                presenter.updateList(establishments);
                presenter.showLoadProgresss(false, "");
            }

            @Override
            public void onFailure(Call<List<Establishment>> call, Throwable t) {

            }
        });
    }
}
