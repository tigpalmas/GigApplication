package com.example.tiago.myapplication.model;

import com.example.tiago.myapplication.domain.Event;
import com.example.tiago.myapplication.domain.News;
import com.example.tiago.myapplication.network.GigProvider;
import com.example.tiago.myapplication.network.GigService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tiago on 17/12/2017.
 */

public class ModelListNews implements MVP.ModelListNews {
    private GigService service;
    private MVP.PresenterListNews presenter;

    public ModelListNews(MVP.PresenterListNews presenter) {
        GigProvider provider = new GigProvider();
        service = provider.getmTService();
        this.presenter = presenter;
    }

    @Override
    public void retrieveNews(String id) {
        getNewsFromServer(id);
    }

    private void getNewsFromServer(String id) {
        Call<List<News>> call = service.getNews(id);
        call.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if(response.isSuccessful()){
                    List<News> news = response.body();
                    if(news.size()>0){
                        presenter.updateList(news);
                        presenter.showLoadProgresss(false, "");
                    }else{
                        presenter.showLoadProgresss(false, "Sem Noticias cadastradas no momento");
                    }
                }else{
                    presenter.showLoadProgresss(false, "error");
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {

            }
        });
    }
}
