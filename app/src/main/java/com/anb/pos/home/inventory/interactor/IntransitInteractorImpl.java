package com.anb.pos.home.inventory.interactor;

import android.text.TextUtils;

import com.anb.pos.ANBApplication;
import com.anb.pos.R;
import com.anb.pos.home.inventory.InventoryAPI;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Bhaumik on 10/04/17.
 */

public class IntransitInteractorImpl implements IntransitInteractor {

    @Override
    public void getDataFromServer(String from_date, String to_date, final OnFinishListner onFinishListner) {

        if (TextUtils.isEmpty(from_date)) {
            onFinishListner.onFailure(R.string.error_invalid_from_date);
        } else if (TextUtils.isEmpty(to_date)) {
            onFinishListner.onFailure(R.string.error_invalid_to_date);
        } else {
            ANBApplication.mInstance.getRetrofit().create(InventoryAPI.class).getDataHeader(from_date, to_date).enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    final JsonObject jsonObject = response.body();
                    final int result = jsonObject.get("result").getAsInt();
                    if (result == 1) {
                        final JsonArray jsonArray = jsonObject.getAsJsonArray("data");
                        onFinishListner.onSuccess(jsonArray);
                    } else {
                        onFinishListner.onError(jsonObject.get("Cause").getAsString());
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    onFinishListner.onFailure(R.string.something_went_wrong);
                }
            });
        }
    }
}
