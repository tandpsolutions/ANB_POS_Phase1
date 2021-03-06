package com.anb.pos.login.interactor;

import android.text.TextUtils;

import com.anb.pos.ANBApplication;
import com.anb.pos.R;
import com.anb.pos.login.LoginAPI;
import com.anb.pos.support.Preference;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Bhaumik on 20/03/17.
 */

public class LoginInteractorImpl implements ILoginInteractor {


    @Override
    public void onValidate(String username, String password, final String branch, final OnFinishListner onFinishListner) {
        if (TextUtils.isEmpty(username)) {
            onFinishListner.onFailure(R.string.error_invalid_email);
        } else if (TextUtils.isEmpty(password)) {
            onFinishListner.onFailure(R.string.error_invalid_password);
        } else {
            ANBApplication.mInstance.getRetrofit().create(LoginAPI.class).validateLogin(username, password, branch).enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    final JsonObject jsonObject = response.body();
                    final int result = jsonObject.get("result").getAsInt();
                    Preference.getInstance().savePreferenceData(Preference.USER_ID, jsonObject.get("USER_ID").getAsString());
                    Preference.getInstance().savePreferenceData(Preference.USER_GRP_CD, jsonObject.get("USER_GRP_CD").getAsString());
                    Preference.getInstance().savePreferenceData(Preference.CURRENT_BRANCH, branch);
                    if (result == 1) {
                        onFinishListner.onSuccess();
                    } else {
                        onFinishListner.onFailure(result);
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
