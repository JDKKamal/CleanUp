package com.jdkgroup.cleanup.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jdkgroup.baseclasses.BaseFragment;
import com.jdkgroup.cleanup.R;
import com.jdkgroup.cleanup.activity.VerifyOTPActivity;
import com.jdkgroup.constant.AppConstant;
import com.jdkgroup.utils.AppUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CustomerEditFragment extends BaseFragment implements View.OnClickListener {

    Unbinder unbinder;
    @BindView (R.id.appBtnEdit)
    AppCompatButton appBtnEdit;

    public CustomerEditFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_edit, null);
        unbinder = ButterKnife.bind(this, view);

        appBtnEdit.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.appBtnEdit:
                AppUtils.lauchFramgentActivity(getActivity(), AppConstant.LAUNCH_VERIFY_OTP_FRAGMENT);
                break;
        }
    }

   @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}