package com.anb.pos.home.inventory.fragments;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.anb.pos.BaseFragment;
import com.anb.pos.R;
import com.anb.pos.custom.SpacesItemDecoration;
import com.anb.pos.home.HomeActivity;
import com.anb.pos.home.inventory.adapters.IntransitAdapter;
import com.anb.pos.home.inventory.model.IntransitModel;
import com.anb.pos.home.inventory.presenter.IntransitPresenterImpl;
import com.anb.pos.home.inventory.view.IntransitView;
import com.anb.pos.support.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

/**
 * A simple {@link BaseFragment} subclass.
 */
public class IntransitFragment extends BaseFragment implements IntransitView {


    private IntransitPresenterImpl intransitPresenter;

    private String frmDate = Utils.getDateChooserPropertyInit();
    private String toDate = Utils.getDateChooserPropertyInit();

    private Dialog searchAlertDialog;

    private ArrayList<IntransitModel.IntransitData> intransitModels;

    private Calendar fromCal = Calendar.getInstance();
    private Calendar toCal = Calendar.getInstance();

    private RecyclerView rvList;

    private IntransitAdapter intransitAdapter;

    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_intransit, container, false);
    }

    @Override
    public void initView(View view) {
        setHasOptionsMenu(true);
        rvList = (RecyclerView) view.findViewById(R.id.frg_intransit_rv_main);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.frg_intransit_swipe_refresh);

        final LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvList.setLayoutManager(gridLayoutManager);
        intransitModels = new ArrayList<>();
        intransitPresenter = new IntransitPresenterImpl(this);
        try {
            intransitPresenter.getDataFromServer(Utils.ConvertDateFormetForDB(frmDate), Utils.ConvertDateFormetForDB(toDate));
        } catch (Exception e) {
            e.printStackTrace();
        }

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                intransitPresenter.getDataFromServer(Utils.ConvertDateFormetForDB(frmDate), Utils.ConvertDateFormetForDB(toDate));
            }
        });
    }

    @Override
    public void trackScreen() {

    }

    @Override
    public void initActionBar() {
        ((HomeActivity) getActivity()).setToolBar(getString(R.string.nav_menu_in_transit), false);
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
        Utils.getInstance().displayMessageDialog(getActivity(), getString(response_code));
    }

    @Override
    public void showError(String message) {
        Utils.getInstance().displayMessageDialog(getActivity(), message);
    }

    @Override
    public void success(JsonArray jsonArray) {
        swipeRefreshLayout.setRefreshing(false);
        TypeToken<List<IntransitModel.IntransitData>> token = new TypeToken<List<IntransitModel.IntransitData>>() {
        };
        intransitModels.clear();
        intransitModels.addAll(new Gson().<Collection<? extends IntransitModel.IntransitData>>fromJson(jsonArray, token.getType()));
        if (intransitAdapter == null) {
            intransitAdapter = new IntransitAdapter(intransitModels, getActivity());
        }
        if (intransitAdapter != null) {
            rvList.setAdapter(intransitAdapter);
            intransitAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_report_filter, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_filter) {
            showSearchTitle();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showSearchTitle() {
        searchAlertDialog = new Dialog(getActivity(), R.style.full_screen_dialog);
        searchAlertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        searchAlertDialog.setContentView(R.layout.dialog_filter_search);
        searchAlertDialog.setCancelable(false);
        searchAlertDialog.setCanceledOnTouchOutside(true);
        final WindowManager.LayoutParams wlmp = searchAlertDialog.getWindow().getAttributes();
        wlmp.gravity = Gravity.TOP;
        searchAlertDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        final ImageView ivBack = (ImageView) searchAlertDialog.findViewById(R.id.ic_back);
        final TextView from_date = (TextView) searchAlertDialog.findViewById(R.id.dialog_filter_tv_from_date);
        final TextView to_date = (TextView) searchAlertDialog.findViewById(R.id.dialog_filter_tv_to_date);
        final TextView submit = (TextView) searchAlertDialog.findViewById(R.id.dialog_filter_tv_submit);

        from_date.setText(frmDate);
        to_date.setText(toDate);
        ivBack.setOnClickListener(this);
        from_date.setOnClickListener(this);
        to_date.setOnClickListener(this);
        submit.setOnClickListener(this);
        searchAlertDialog.show();
    }


    @Override
    public void onClick(final View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ic_back:
                searchAlertDialog.dismiss();
                break;
            case R.id.dialog_filter_tv_from_date:
                DatePickerDialog fromPicker = DatePickerDialog.newInstance(
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                ((TextView) v).setText(((dayOfMonth < 10) ? "0" + dayOfMonth : dayOfMonth) + "/" + ((monthOfYear + 1 < 10) ? "0" + (monthOfYear + 1) : monthOfYear + 1) + "/" + year);
                                frmDate = ((TextView) v).getText().toString();
                                fromCal.set(Calendar.YEAR, year);
                                fromCal.set(Calendar.MONTH, monthOfYear);
                                fromCal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                            }
                        },
                        fromCal.get(Calendar.YEAR),
                        fromCal.get(Calendar.MONTH),
                        fromCal.get(Calendar.DAY_OF_MONTH)
                );
                fromPicker.show(getActivity().getFragmentManager(), "Datepickerdialog");
                break;
            case R.id.dialog_filter_tv_to_date:
                DatePickerDialog toPicker = DatePickerDialog.newInstance(
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                ((TextView) v).setText(((dayOfMonth < 10) ? "0" + dayOfMonth : dayOfMonth) + "/" + ((monthOfYear + 1 < 10) ? "0" + (monthOfYear + 1) : monthOfYear + 1) + "/" + year);
                                toDate = ((TextView) v).getText().toString();
                                toCal.set(Calendar.YEAR, year);
                                toCal.set(Calendar.MONTH, monthOfYear);
                                toCal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                            }
                        },
                        toCal.get(Calendar.YEAR),
                        toCal.get(Calendar.MONTH),
                        toCal.get(Calendar.DAY_OF_MONTH)
                );
                toPicker.show(getActivity().getFragmentManager(), "Datepickerdialog");
                break;
            case R.id.dialog_filter_tv_submit:
                try {
                    searchAlertDialog.dismiss();
                    intransitPresenter.getDataFromServer(Utils.ConvertDateFormetForDB(frmDate), Utils.ConvertDateFormetForDB(toDate));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

        }
    }
}
