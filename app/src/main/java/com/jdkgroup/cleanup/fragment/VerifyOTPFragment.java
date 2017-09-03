package com.jdkgroup.cleanup.fragment;

import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jdkgroup.baseclasses.BaseActivity;
import com.jdkgroup.baseclasses.BaseFragment;
import com.jdkgroup.cleanup.R;
import com.jdkgroup.constant.AppConstant;
import com.jdkgroup.customviews.VerificationAction;
import com.jdkgroup.customviews.VerificationCodeEditText;
import com.jdkgroup.utils.AppUtils;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class VerifyOTPFragment extends BaseFragment implements View.OnClickListener, VerificationAction.OnVerificationCodeChangedListener {

    Unbinder unbinder;
    @BindView (R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @BindView (R.id.verificationCodeEditText)
    VerificationCodeEditText verificationCodeEditText;
    @BindView (R.id.appBtnVerifyOTPResend)
    AppCompatButton appBtnVerifyOTPResend;
    @BindView (R.id.appBtnOTPVerify)
    AppCompatButton appBtnOTPVerify;
    @BindView (R.id.appTvTimer)
    AppCompatTextView appTvTimer;

    private int resultCode;
    private CountDownTimer countDownTimer;
    int noOfMinutes = 5 * 60 * 1000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_verify_otp, null);

        return view;
    }

    @RequiresApi (api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        setCountDownTimer(noOfMinutes);

        appBtnVerifyOTPResend.setOnClickListener(this);
        appBtnOTPVerify.setOnClickListener(this);
        verificationCodeEditText.setOnVerificationCodeChangedListener(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onVerCodeChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void onInputCompleted(CharSequence s) {
        AppUtils.showToast(getActivity(), s.toString());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.appBtnVerifyOTPResend:
                verificationCodeEditText.setText(null);
                countDownTimer.cancel();
                countDownTimer = null;
                setCountDownTimer(noOfMinutes);
                break;
            case R.id.appBtnOTPVerify:
                showSnakBar(coordinatorLayout, getString(R.string.msg_otp_verify_success));
                verificationCodeEditText.setText(null);
                getResultCode();
                AppUtils.lauchFramgentActivity(getActivity(), AppConstant.LAUNCH_CUSTOMER_LIST_FRAGMENT);
                break;
        }
    }

    public void getResultCode() {
        //launch(BaseDrawerActivity.class);
        //finish();
    }

    private void setCountDownTimer(int noOfMinutes) {
        countDownTimer = new CountDownTimer(noOfMinutes, 1000) {
            public void onTick(long millisUntilFinished) {
                appBtnVerifyOTPResend.setEnabled(false);
                long millis = millisUntilFinished;
                String hms = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                appTvTimer.setText(hms);
            }

            public void onFinish() {
                appBtnVerifyOTPResend.setEnabled(true);
                appTvTimer.setText(R.string.timer);
            }
        }.start();

    }

    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        countDownTimer.cancel();
        countDownTimer = null;
    }
}