package com.anb.pos.branch.interactor;

import android.util.Log;

import com.anb.pos.ANBApplication;
import com.anb.pos.R;
import com.anb.pos.branch.BranchMasterAPI;
import com.anb.pos.branch.model.BranchModelData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Bhaumik on 20/03/17.
 */

public class BranchInteractorImpl implements IBranchInteractor {


    @Override
    public void getAllBranch(final OnGetBranchListner onGetBranchListner) {

        ANBApplication.mInstance.getRetrofit().create(BranchMasterAPI.class).GetBranchMaster().enqueue(new Callback<BranchModelData>() {
            @Override
            public void onResponse(Call<BranchModelData> call, Response<BranchModelData> response) {
                onGetBranchListner.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<BranchModelData> call, Throwable t) {
                Log.e("error", t.getMessage());
                onGetBranchListner.onError(R.string.something_went_wrong);
            }
        });
    }
}
