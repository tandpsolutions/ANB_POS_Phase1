package com.anb.pos.login.interactor;

/**
 * Created by indianic on 20/03/17.
 */

public interface ILoginInteractor {
    void onValidate(String username, String password, String branch, OnFinishListner onFinishListner);

    interface OnFinishListner {
        void onSuccess();


        void onFailure(int response_code);
    }
}
