package com.example.tiago.myapplication.presenter;

import com.example.tiago.myapplication.domain.Bonus;
import com.example.tiago.myapplication.model.MVP;
import com.example.tiago.myapplication.model.ModelBonus;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiago on 29/12/2017.
 */

public class PresenterListBonus implements MVP.PresenterListBonus {
    private MVP.ModelListBonus model;
    private MVP.ViewListBonus view;
    private List<Bonus> mBonus = new ArrayList<>();

    public PresenterListBonus() {
        this.model = new ModelBonus(this);
    }


    @NotNull
    @Override
    public List<Bonus> getBonus() {
        return mBonus;
    }

    @Override
    public void retrieveBonus(@NotNull String id) {
        model.retrieveBonus(id);
    }

    @Override
    public void updateList(@NotNull List<Bonus> bonus) {
        mBonus.addAll(bonus);
        view.updateList();
    }

    @Override
    public void setView(@NotNull MVP.ViewListBonus view) {
        this.view = view;
    }

    @Override
    public void showLoadProgresss(boolean status, @NotNull String message) {
        view.showLoadProgresss(status, message);
    }

}
