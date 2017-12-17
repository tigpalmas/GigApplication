package com.example.tiago.myapplication.network;

import com.example.tiago.myapplication.domain.Establishment;
import com.example.tiago.myapplication.domain.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by tiago on 17/12/2017.
 */

public interface GigService {

    @GET("/establishment")
    Call<List<Establishment>> getEstablishments();

    @GET("/event/establishment/{id}")
    Call<List<Event>> getEvents(@Path("id") String id);
}
