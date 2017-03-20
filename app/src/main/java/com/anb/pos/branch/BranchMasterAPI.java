package com.anb.pos.branch;

import com.anb.pos.branch.model.BranchModelData;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Bhaumik on 20/03/17.
 */

public interface BranchMasterAPI {
    @GET("GetBranchMaster")
    Call<BranchModelData> GetBranchMaster();
}
