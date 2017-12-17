package com.example.tiago.myapplication.network;


import com.example.tiago.myapplication.utils.ClientConfigs;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tiago on 30/01/2017.
 */

public class GigProvider {
    private GigService mTService;
    private Retrofit mRetrofitClient;

    public GigProvider(){

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new UTCDateTypeAdapter())
                .create();

        mRetrofitClient = new Retrofit.Builder()
                .baseUrl(ClientConfigs.REST_API_BASER_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        mTService = mRetrofitClient.create(GigService.class);
    }

    public GigService getmTService() {
        return mTService;
    }

    public Retrofit getRetrofitClient(){
        return mRetrofitClient;
    }

}
