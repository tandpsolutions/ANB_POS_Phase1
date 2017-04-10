package com.anb.pos.home.inventory.view;

import com.google.gson.JsonArray;

/**
 * Created by Bhaumik on 10/04/17.
 */

public interface IntransitView {
    void showLoading();

    void dismissLoading();

    void showError(int response_code);

    void showError(String message);

    void success(final JsonArray array);
}
