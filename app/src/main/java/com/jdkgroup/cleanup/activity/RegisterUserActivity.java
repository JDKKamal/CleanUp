package com.jdkgroup.cleanup.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.jdkgroup.baseclasses.SimpleMVPActivity;
import com.jdkgroup.cleanup.R;
import com.jdkgroup.cleanup.dialog.SpinnerDialog;
import com.jdkgroup.constant.AppConstant;
import com.jdkgroup.cleanup.database.RealmController;
import com.jdkgroup.cleanup.model.api.CountryModel;
import com.jdkgroup.presenter.RegisterUserPresenter;
import com.jdkgroup.utils.AppUtils;
import com.jdkgroup.view.RegisterUserView;

import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class RegisterUserActivity extends SimpleMVPActivity<RegisterUserPresenter, RegisterUserView> implements RegisterUserView, View.OnClickListener {

    @BindView (R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @BindView (R.id.toolBar)
    Toolbar toolBar;
    @BindView (R.id.appEdtUserName)
    AppCompatEditText appEdtUserName;
    @BindView (R.id.appEdtEmail)
    AppCompatEditText appEdtEmail;
    @BindView (R.id.appEdtPassword)
    AppCompatEditText appEdtPassword;
    @BindView (R.id.appIvPasswordShow)
    AppCompatImageView appIvPasswordShow;
    @BindView (R.id.appEdtConfirmPassword)
    AppCompatEditText appEdtConfirmPassword;
    @BindView (R.id.appIvConfirmPasswordShow)
    AppCompatImageView appIvConfirmPasswordShow;
    @BindView (R.id.appEdtMobile)
    AppCompatEditText appEdtMobile;
    @BindView (R.id.appEdtCountry)
    AppCompatEditText appEdtCountry;
    @BindView (R.id.appBtnRegisterUser)
    AppCompatButton appBtnRegisterUser;

    private Realm realm;
    private RealmController realmController;
    private CountryModel selectedCountry = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        hideSoftKeyboard();

        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        realmController = new RealmController(realm);

        setSupportActionBar(toolBar);
        toolBarSetFont(toolBar);
        setTitle(getString(R.string.title_register_user));

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        try {
            realmController.deleteAllCountryData();
            String UUIDCurrentTimeMillis = System.currentTimeMillis() + "-" + UUID.randomUUID().toString();
            String jsonCountry = AppUtils.readFileFromAssets(getActivity(), AppConstant.READ_FILE_JSON_COUNTRY, AppConstant.EXTENSION_JSON);

            List<CountryModel> alCountryModel = AppUtils.getToList(jsonCountry, CountryModel.class);
            CountryModel countryModel;
            for (CountryModel country : alCountryModel) {
                countryModel = new CountryModel(UUIDCurrentTimeMillis, country.getName(), country.getCode());
                realmController.addData(countryModel);
            }
        } catch (Exception ex) {
            showSnakBar(coordinatorLayout, getString(R.string.msg_no_data));
            ex.printStackTrace();
        }

        toolBar.setNavigationOnClickListener(arrow -> finish());

        appBtnRegisterUser.setOnClickListener(this);
        appEdtCountry.setOnClickListener(this);
    }

    @NonNull
    @Override
    public RegisterUserPresenter createPresenter() {
        return new RegisterUserPresenter();
    }

    @NonNull
    @Override
    public RegisterUserView attachView() {
        return this;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.appBtnRegisterUser:
                String username, email, password, confirmpassword, mobile;
                username = appEdtUserName.getText().toString().trim();
                email = appEdtEmail.getText().toString().trim();
                password = appEdtPassword.getText().toString().trim();
                confirmpassword = appEdtConfirmPassword.getText().toString().trim();
                mobile = appEdtMobile.getText().toString().trim();

                if (isValidation(username, email, password, confirmpassword, mobile) == true) {
                    hideSoftKeyboard();
                    launch(VerifyOTPActivity.class);
                }
                break;

            case R.id.appEdtCountry:
                hideSoftKeyboard();
                getPresenter().getCountryList(realmController);

                break;
        }
    }

    private boolean isValidation(final String username, final String email, final String password, final String confirmpassword, final String mobile) {

        if (TextUtils.isEmpty(username)) {
            showSnakBar(coordinatorLayout, getString(R.string.msg_empty_username));
            return false;
        } else if (TextUtils.isEmpty(email)) {
           showSnakBar(coordinatorLayout, getString(R.string.msg_empty_email));
            return false;
        } else if (TextUtils.isEmpty(password)) {
           showSnakBar(coordinatorLayout, getString(R.string.msg_empty_password));
            return false;
        } else if (TextUtils.isEmpty(confirmpassword)) {
            showSnakBar(coordinatorLayout, getString(R.string.msg_empty_confirm_password));
            return false;
        } else if (TextUtils.isEmpty(mobile)) {
           showSnakBar(coordinatorLayout, getString(R.string.msg_empty_mobile));
            return false;
        }
        return true;
    }

    @Override
    public void responseCountryList(List<?> response) {
        if (response.size() > 0) {
            SpinnerDialog sd = new SpinnerDialog(this, AppUtils.getStringFromId(this, R.string.title_country), new SpinnerDialog.OnItemClick() {

                @Override
                public void selectedItem(Object object) {
                    selectedCountry = (CountryModel) object;
                    appEdtCountry.setText(selectedCountry.getName());
                }
            }, response);
            sd.show();
        } else {
            showSnakBar(coordinatorLayout, getString(R.string.msg_no_data));
        }
    }

    @Override
    public void onFailure(String message) {
    }
}