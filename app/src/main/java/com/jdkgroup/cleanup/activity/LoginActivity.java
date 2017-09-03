package com.jdkgroup.cleanup.activity;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;

import com.jdkgroup.baseclasses.BaseActivity;
import com.jdkgroup.cleanup.BaseDrawerActivity;
import com.jdkgroup.cleanup.R;
import com.jdkgroup.utils.AppUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    //http://en.proft.me/2016/12/30/realm-database-tutorial-android/
    @BindView (R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @BindView (R.id.appEdtEmail)
    AppCompatEditText appEdtEmail;
    @BindView (R.id.appEdtPassword)
    AppCompatEditText appEdtPassword;
    @BindView (R.id.appIvPasswordShow)
    AppCompatImageView appIvPasswordShow;
    @BindView (R.id.appTvForgotPassword)
    AppCompatTextView appTvForgotPassword;
    @BindView (R.id.appBtnLogin)
    AppCompatButton appBtnLogin;
    @BindView (R.id.appTvRegister)
    AppCompatTextView appTvRegister;
    @BindView (R.id.appIvTwitter)
    AppCompatImageView appIvTwitter;
    @BindView (R.id.appIvFacebook)
    AppCompatImageView appIvFacebook;

    private boolean flagPasswordShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        hideSoftKeyboard();

        flagPasswordShow = true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        ButterKnife.bind(this);

        appBtnLogin.setOnClickListener(this);
        appTvRegister.setOnClickListener(this);
        appIvPasswordShow.setOnClickListener(this);
        appTvForgotPassword.setOnClickListener(this);
    }

    private void isPasswordShow(AppCompatEditText appCompatEditText) {
        if (flagPasswordShow == true) {
            flagPasswordShow = false;
            appCompatEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else if (flagPasswordShow == false) {
            flagPasswordShow = true;
            appCompatEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        appCompatEditText.setSelection(appCompatEditText.length());
    }

    private boolean isValidation(final String email, final String password) {
        if (TextUtils.isEmpty(email)) {
            showSnakBar(coordinatorLayout, getString(R.string.msg_empty_email));
            return false;
        } else if (TextUtils.isEmpty(password)) {
            showSnakBar(coordinatorLayout, getString(R.string.msg_empty_password));
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.appBtnLogin:
                String email, password;
                email = appEdtEmail.getText().toString().trim();
                password = appEdtPassword.getText().toString().trim();

                if (isValidation(email, password) == true) {
                    appEdtEmail.setText(null);
                    appEdtPassword.setText(null);
                }

                launch(BaseDrawerActivity.class);
                break;

            case R.id.appTvRegister:
                appEdtPassword.setText(null);
                launch(RegisterUserActivity.class);
                break;

            case R.id.appTvForgotPassword:
                appEdtPassword.setText(null);
                launch(ForgotPasswordActivity.class);
                break;

            case R.id.appIvPasswordShow:
                isPasswordShow(appEdtPassword);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        AppUtils.appExist(getActivity());
    }
}