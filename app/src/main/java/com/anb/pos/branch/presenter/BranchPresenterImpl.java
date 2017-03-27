package com.anb.pos.branch.presenter;

import com.anb.pos.branch.interactor.BranchInteractorImpl;
import com.anb.pos.branch.interactor.IBranchInteractor;
import com.anb.pos.branch.model.BranchModelData;
import com.anb.pos.branch.view.IBranchView;

/**
 * Created by Bhaumik on 20/03/17.
 */

public class BranchPresenterImpl implements IBranchPresenter, IBranchInteractor.OnGetBranchListner {


    private IBranchView iBranchView;
    private BranchInteractorImpl branchInteractor;

    public BranchPresenterImpl(IBranchView iBranchView) {
        this.iBranchView = iBranchView;
        branchInteractor = new BranchInteractorImpl();
    }

    @Override
    public void getAllBranch() {
        branchInteractor.getAllBranch(this);
    }


    @Override
    public void onSuccess(BranchModelData branchModel) {
        iBranchView.onGetBranchSuccess(branchModel);
    }

    @Override
    public void onError(int response_code) {
        iBranchView.onError(response_code);
    }
}
