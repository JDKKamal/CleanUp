package com.jdkgroup.cleanup.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

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
    @BindView (R.id.rlLeadQuality)
    RelativeLayout rlLeadQuality;
    @BindView (R.id.appEdtLeadQuality)
    AppCompatEditText appEdtLeadQuality;

    public LeadDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lead_details, null);
        unbinder = ButterKnife.bind(this, view);
        setHasOptionsMenu(true);

        appTvLeadBasicEdit.setOnClickListener(this);
        appTvLeadEditProperties.setOnClickListener(this);
        appEdtLeadQuality.setOnClickListener(this);
        rlLeadQuality.setOnClickListener(this);
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
    public void onPrepareOptionsMenu(Menu menu) {
        menu.setGroupVisible(R.id.action_group_lead, true);
        menu.findItem(R.id.action_lead_create).setVisible(false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_lead_create:
                AppUtils.hideKeyboard(getActivity());
                AppUtils.lauchFramgentActivity(getActivity(), AppConstant.LAUNCH_LEAD_SEARCH_TO_CREATE_FRAGMENT);
                return false;

            default:
                break;
        }

        return false;
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

            case R.id.appEdtLeadQuality:


                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}