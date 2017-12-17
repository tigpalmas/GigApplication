package com.example.tiago.myapplication.presenter;

import com.example.tiago.myapplication.domain.Establishment;
import com.example.tiago.myapplication.model.MVP;
import com.example.tiago.myapplication.model.ModelListEstablishment;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiago on 09/12/2017.
 */

public class PresenterListEstablishment implements MVP.PresenterListEstablishments {
    private MVP.ModelListEstablishments model;
    private MVP.ViewListEstablishment view;
    private List<Establishment> mEstablishments = new ArrayList<>();
    private List<Establishment> filterEstablishments ;

    public PresenterListEstablishment() {
        model = new ModelListEstablishment(this);
    }


    @Override
    public void setView(@NotNull MVP.ViewListEstablishment view) {
        this.view = view;
    }


    @Override
    public void retrieveEstablishments() {
        model.retrieveEstablishments();

    }

    @NotNull
    @Override
    public List<Establishment> getEstablishment() {
        return this.mEstablishments;
    }

    @Override
    public void updateList(@NotNull List<Establishment> establishments) {
        mEstablishments.addAll(establishments);
        view.updateList();
    }


    @Override
    public void showLoadProgresss(boolean status, @NotNull String message) {
        view.showLoadProgresss(status, message);
    }

    @Override
    public void buscar(@Nullable String s) {
        if (s == null || s.trim().equals("")) {
            limparBusca();
            return;
        }

        List<Establishment> foundEstablishments = new ArrayList<Establishment>(mEstablishments);
        for (int i = foundEstablishments.size() - 1; i >= 0; i--) {
            Establishment establishment = foundEstablishments.get(i);
            if (!establishment.getPersonalDataId().getName().toUpperCase().contains(s.toUpperCase())) {
                foundEstablishments.remove(establishment);
            }
        }
      view.filterEstablishmenn(foundEstablishments);
    }

    @Override
    public void limparBusca() {
        view.filterEstablishmenn(mEstablishments);
    }


}
