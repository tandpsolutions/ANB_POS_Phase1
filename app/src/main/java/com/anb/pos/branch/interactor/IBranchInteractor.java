package com.anb.pos.branch.interactor;

import com.anb.pos.branch.model.BranchModelData;

import java.util.ArrayList;

/**
 * Created by indianic on 20/03/17.
 */

public interface IBranchInteractor {

    void getAllBranch(OnGetBranchListner onGetBranchListner);

    interface OnGetBranchListner {
        void onSuccess(BranchModelData branchModel);

        void onError(int response_code);
    }
}
