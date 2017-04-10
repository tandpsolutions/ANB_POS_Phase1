package com.anb.pos.home.inventory.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anb.pos.BaseFragment;
import com.anb.pos.R;
import com.anb.pos.home.inventory.presenter.IntransitPresenterImpl;
import com.anb.pos.home.inventory.view.IntransitView;
import com.anb.pos.support.Utils;
import com.google.gson.JsonArray;

/**
 * A simple {@link BaseFragment} subclass.
 */
public class IntransitFragment extends BaseFragment implements IntransitView {


    private IntransitPresenterImpl intransitPresenter;
    private String from_date;
    private String to_date;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_intransit, container, false);
    }

    @Override
    public void initView(View view) {
        intransitPresenter = new IntransitPresenterImpl(this);
        intransitPresenter.getDataFromServer(from_date, to_date);
    }

    @Override
    public void trackScreen() {

    }

    @Override
    public void initActionBar() {

    }

    @Override
    public void showLoading() {
        Utils.getInstance().showProgressDialog(getActivity(), getString(R.string.loading), false);
    }

    @Override
    public void dismissLoading() {
        Utils.getInstance().dismissProgressDialog();
    }

    @Override
    public void showError(int response_code) {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void success(JsonArray jsonArray) {

    }
}
