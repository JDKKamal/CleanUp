package com.jdkgroup.cleanup.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jdkgroup.baseclasses.BaseFragment;
import com.jdkgroup.cleanup.R;
import com.jdkgroup.constant.AppConstant;
import com.jdkgroup.utils.AppUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LeadDetailsFragment extends BaseFragment implements View.OnClickListener {

    Unbinder unbinder;
    @BindView (R.id.appTvLeadBasicEdit)
    AppCompatTextView appTvLeadBasicEdit;
    @BindView (R.id.appTvLeadEditProperties)
    AppCompatTextView appTvLeadEditProperties;

    public LeadDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lead_details, null);
        unbinder = ButterKnife.bind(this, view);

        appTvLeadBasicEdit.setOnClickListener(this);
        appTvLeadEditProperties.setOnClickListener(this);
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
            case R.id.appTvLeadBasicEdit:
                AppUtils.lauchFramgentActivity(getActivity(), AppConstant.LAUNCH_LEAD_CREATE_BASIC_EDIT_FRAGMENT);
                break;

            case R.id.appTvLeadEditProperties:
                AppUtils.lauchFramgentActivity(getActivity(), AppConstant.LAUNCH_LEAD_EDIT_FRAGMENT);
                break;
        }
    }

   @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}