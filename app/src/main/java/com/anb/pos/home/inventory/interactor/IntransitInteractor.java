package com.anb.pos.home.inventory.interactor;


import com.google.gson.JsonArray;

/**
 * Created by Bhaumik on 10/04/17.
 */

public interface IntransitInteractor {

    void getDataFromServer(final String from_date, final String to_date, final OnFinishListner onFinishListner);

    interface OnFinishListner {
        void onSuccess(final JsonArray array);

        void onError(String message);

        void onFailure(int response_code);
    }
}
