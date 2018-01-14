package com.example.tiago.myapplication.model;

import com.example.tiago.myapplication.domain.Bonus;
import com.example.tiago.myapplication.domain.Event;
import com.example.tiago.myapplication.domain.FilterTimeline;
import com.example.tiago.myapplication.domain.News;
import com.example.tiago.myapplication.domain.TimelineModel;
import com.example.tiago.myapplication.network.GigProvider;
import com.example.tiago.myapplication.network.GigService;

import org.jetbrains.annotations.NotNull;

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
    public void retriveData(@NotNull FilterTimeline model) {
        getObjectsFromServer(model);
    }

    @Override
    public void retriveEventsData() {
        getEvensFromServer();
    }

    @Override
    public void retriveBonusData() {
        getBonusFromServer();
    }

    @Override
    public void retriveNewsData() {
        getNewsFromServer();
    }

    private void getObjectsFromServer(@NotNull final FilterTimeline model){
        Call<List<TimelineModel>> call = service.getObjects(model);
        call.enqueue(new Callback<List<TimelineModel>>() {
            @Override
            public void onResponse(Call<List<TimelineModel>> call, Response<List<TimelineModel>> response) {
                if(response.isSuccessful()){
                    List<TimelineModel> objects = response.body();
                    if(objects.size()>0){
                        presenter.updateList(objects);
                        presenter.showLoadProgresss(false, "");
                    }else{
                        if(model.date!=null){

                           presenter.showLoadProgresss(false, "Sem eventos cadastrados para o dia "+model.date);
                        }else{
                            presenter.showLoadProgresss(false, "Sem eventos cadastrados no momento");
                        }

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

    private void getEvensFromServer() {
        Call<List<Event>> call = service.getEventsLine();
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if(response.isSuccessful()){
                    List<Event> events = response.body();
                    if(events.size()>0){
                        presenter.updateListEvents(events);
                        presenter.showLoadProgresss(false, "");
                    }else{
                        presenter.showLoadProgresss(false, "Sem eventos cadastrados no momento");
                    }
                }else{
                    presenter.showLoadProgresss(false, "error");
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                presenter.showLoadProgresss(false, t.getMessage());
            }
        });
    }

    private void getBonusFromServer() {
        Call<List<Bonus>> call = service.getBonusLine();
        call.enqueue(new Callback<List<Bonus>>() {
            @Override
            public void onResponse(Call<List<Bonus>> call, Response<List<Bonus>> response) {
                if(response.isSuccessful()){
                    List<Bonus> bonus = response.body();
                    if(bonus.size()>0){
                        presenter.updateListBonus(bonus);
                        presenter.showLoadProgresss(false, "");
                    }else{
                        presenter.showLoadProgresss(false, "Sem bonus cadastrados no momento");
                    }
                }else{
                    presenter.showLoadProgresss(false, "error");
                }
            }

            @Override
            public void onFailure(Call<List<Bonus>> call, Throwable t) {

            }
        });

    }

    private void getNewsFromServer() {
        Call<List<News>> call = service.getNewsLine();
        call.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if(response.isSuccessful()){
                    List<News> news = response.body();
                    if(news.size()>0){
                        presenter.updateListNews(news);
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
