package com.anb.pos.branch.view;

import com.anb.pos.branch.model.BranchModelData;

/**
 * Created by Bhaumik on 20/03/17.
 */

public interface IBranchView {

    void onGetBranchSuccess(BranchModelData data);

    void onError(int response_code);

}
