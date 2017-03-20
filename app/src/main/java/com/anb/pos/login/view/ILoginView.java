package com.anb.pos.login.view;

/**
 * Created by indianic on 20/03/17.
 */

public interface ILoginView {

    void showLoading();

    void dismissLoading();

    void showError(int response_code);

    void success();
}
