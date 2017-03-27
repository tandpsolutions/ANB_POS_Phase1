package com.anb.pos.login.presenter;

import com.anb.pos.login.interactor.ILoginInteractor;
import com.anb.pos.login.interactor.LoginInteractorImpl;
import com.anb.pos.login.view.ILoginView;

/**
 * Created by Bhaumik on 20/03/17.
 */

public class LoginPresenterImpl implements ILoginPresenter, ILoginInteractor.OnFinishListner {
    private ILoginView iLoginView;
    private LoginInteractorImpl loginModel;

    public LoginPresenterImpl(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        loginModel = new LoginInteractorImpl();
    }

    @Override
    public void login(String userName, String password, String branch_code) {
        iLoginView.showLoading();
        loginModel.onValidate(userName, password, branch_code, this);
    }

    @Override
    public void onSuccess() {
        iLoginView.dismissLoading();
        iLoginView.success();
    }

    @Override
    public void onFailure(int response_code) {
        iLoginView.dismissLoading();
        iLoginView.showError(response_code);
    }
}
