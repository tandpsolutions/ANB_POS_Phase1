package com.anb.pos;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.anb.pos.branch.BranchManager;
import com.anb.pos.branch.model.BranchModelData;
import com.anb.pos.branch.presenter.BranchPresenterImpl;
import com.anb.pos.branch.view.IBranchView;
import com.anb.pos.home.HomeActivity;
import com.anb.pos.login.LoginActivity;
import com.anb.pos.support.Preference;

public class SplashActivity extends AppCompatActivity implements IBranchView {

    private Handler handler;
    private int SPLASH_TIME_OUT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler = new Handler();
        handler.postDelayed(runnable, SPLASH_TIME_OUT);
    }

    final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            BranchPresenterImpl branchPresenter = new BranchPresenterImpl(SplashActivity.this);
            branchPresenter.getAllBranch();
        }
    };

    @Override
    public void onGetBranchSuccess(BranchModelData data) {
        BranchManager.getInstance().setBranches(data.getData());
        final String user_id = Preference.getInstance().mSharedPreferences.getString(Preference.USER_ID, "");
        final Intent intent;
        if (user_id.equalsIgnoreCase("")) {
            intent = new Intent(SplashActivity.this, LoginActivity.class);
        } else {
            intent = new Intent(SplashActivity.this, HomeActivity.class);
        }
        startActivity(intent);
        finish();
    }

    @Override
    public void onError(int response_code) {

    }
}
