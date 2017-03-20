package com.anb.pos.login.interactor;

import android.text.TextUtils;

import com.anb.pos.R;

/**
 * Created by indianic on 20/03/17.
 */

public class LoginInteractorImpl implements ILoginInteractor {


    @Override
    public void onValidate(String username, String password, int branch, OnFinishListner onFinishListner) {
        if (TextUtils.isEmpty(username)) {
            onFinishListner.onFailure(R.string.error_invalid_email);
        } else if (TextUtils.isEmpty(username)) {
            onFinishListner.onFailure(R.string.error_invalid_password);
        } else {
            onFinishListner.onSuccess();
        }


    }
}
