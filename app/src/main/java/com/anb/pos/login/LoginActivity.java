package com.anb.pos.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.anb.pos.R;
import com.anb.pos.branch.BranchAdapter;
import com.anb.pos.branch.BranchManager;
import com.anb.pos.login.presenter.LoginPresenterImpl;
import com.anb.pos.login.view.ILoginView;
import com.anb.pos.support.Utils;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements ILoginView {

    private LoginPresenterImpl loginPresenter;

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private Spinner mBranchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.email);
        mBranchView = (Spinner) findViewById(R.id.branch);

        loginPresenter = new LoginPresenterImpl(this);

        mPasswordView = (EditText) findViewById(R.id.password);
        final TextView mEmailSignInButton = (TextView) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
//                attemptLogin();
                Utils.getInstance().hideSoftKeyBoard(LoginActivity.this);
                loginPresenter.login(mEmailView.getText().toString(), mPasswordView.getText().toString(), BranchManager.getInstance().getBranches().get(mBranchView.getSelectedItemPosition()).getBranch_cd());
            }
        });

        final SpinnerAdapter spinnerAdapter = new BranchAdapter(this, R.layout.row_spinner_text, BranchManager.getInstance().getBranches());
        mBranchView.setAdapter(spinnerAdapter);

    }

    @Override
    public void showLoading() {
        Utils.getInstance().showProgressDialog(this, getString(R.string.loading), false);

    }

    @Override
    public void dismissLoading() {
        Utils.getInstance().dismissProgressDialog();
    }

    @Override
    public void showError(int response_code) {
        if (response_code == 0) {
            Utils.getInstance().displayMessageDialog(this, getString(R.string.error_invalid_branch));
        } else if (response_code == -1) {
            Utils.getInstance().displayMessageDialog(this, getString(R.string.error_username_password_invalid));
        } else {
            Utils.getInstance().displayMessageDialog(this, getString(response_code));
        }
    }

    @Override
    public void success() {
        Utils.getInstance().displayMessageDialog(this, "Success");
    }
}

