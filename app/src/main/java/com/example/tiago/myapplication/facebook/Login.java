package com.example.tiago.myapplication.facebook;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.tiago.myapplication.domain.User;
import com.example.tiago.myapplication.model.MVP;
import com.sromku.simple.fb.Permission;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.entities.Profile;
import com.sromku.simple.fb.listeners.OnLoginListener;
import com.sromku.simple.fb.listeners.OnProfileListener;

import java.util.List;


/**
 * Created by tiago on 13/03/2017.
 */

public class Login {
    private Context ctx;
    private SimpleFacebook fb;

    private MVP.ModelLogin model;


    Profile.Properties properties = new Profile.Properties.Builder()
            .add(Profile.Properties.ID)
            .add(Profile.Properties.FIRST_NAME)
            .add(Profile.Properties.LAST_NAME)
            .add(Profile.Properties.GENDER)
            .add(Profile.Properties.EMAIL)
            .add(Profile.Properties.PICTURE)
            .build();

    public Login(Context ctx, MVP.ModelLogin model) {
        this.ctx = ctx;
        SimpleFacebook.setConfiguration(new MyFacebookConfiguration().getMyConfig());
        this.model = model;
        fb = SimpleFacebook.getInstance(model.getActivity());


    }

    public void login() {
        model.showProgressbar(true, "Carregando, aguarde...");
        fb.login(loginListener);
    }

    OnLoginListener loginListener = new OnLoginListener() {
        @Override
        public void onLogin(String accessToken, List<Permission> acceptedPermissions, List<Permission> declinedPermissions) {
            fb.getProfile(properties, onProfileListener);
        }

        @Override
        public void onCancel() {
             model.showProgressbar(false, "Cancelado");
            model.showToast("Cancelado");

        }

        @Override
        public void onException(Throwable throwable) {
            model.showProgressbar(false, "");
            model.showToast("Ops, algo deu errado, "+ throwable.getMessage()+ ", tente novamente");

        }

        @Override
        public void onFail(String reason) {
            model.showProgressbar(false, "");
            model.showToast("Ops, algo deu errado, "+ reason+ ", tente novamente");

        }
    };


    OnProfileListener onProfileListener = new OnProfileListener() {
        @Override
        public void onComplete(final Profile profile) {

            if(profile!=null) {
               User user = new User();
                if(profile.getFirstName()!=null){
                    user.name = profile.getFirstName();
                }
                if(profile.getLastName()!=null){
                    user.lastName = profile.getLastName();
                }
                if(profile.getEmail()!=null){
                    user.email = profile.getEmail();
                }
                if(profile.getPicture()!=null){
                    user.imageUrl = profile.getPicture();
                }
                if(profile.getId()!=null){
                    user.faceId = profile.getId();
                    user.password = profile.getId();
                }
                if(profile.getGender()!=null){
                    user.gender = profile.getGender();
                }
                model.loginServer(user);
            }
        }
    };


    public void onActivityResult(int requestCode, int resultCode, Intent data){
        fb.onActivityResult(requestCode, resultCode, data);
    }





}
