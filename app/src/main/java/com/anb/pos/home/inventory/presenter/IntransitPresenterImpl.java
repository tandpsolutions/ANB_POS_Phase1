package com.anb.pos.home.inventory.presenter;

import com.anb.pos.home.inventory.interactor.IntransitInteractor;
import com.anb.pos.home.inventory.interactor.IntransitInteractorImpl;
import com.anb.pos.home.inventory.view.IntransitView;
import com.google.gson.JsonArray;

/**
 * Created by Bhaumik on 10/04/17.
 */

public class IntransitPresenterImpl implements IntransitPresenter, IntransitInteractor.OnFinishListner {

    private IntransitView intransitView;
    private IntransitInteractorImpl intransitInteractor;

    public IntransitPresenterImpl(IntransitView intransitView) {
        this.intransitView = intransitView;
        intransitInteractor = new IntransitInteractorImpl();
    }

    @Override
    public void getDataFromServer(String from_date, String to_date) {
        intransitView.showLoading();
        intransitInteractor.getDataFromServer(from_date, to_date, this);
    }

    @Override
    public void onSuccess(JsonArray array) {
        intransitView.dismissLoading();
        intransitView.success(array);
    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onFailure(int response_code) {
        intransitView.dismissLoading();
        intransitView.showError(response_code);
    }
}
