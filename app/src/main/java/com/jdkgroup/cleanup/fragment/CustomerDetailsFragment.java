package com.jdkgroup.cleanup.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.jdkgroup.baseclasses.BaseFragment;
import com.jdkgroup.cleanup.R;
import com.jdkgroup.constant.AppConstant;
import com.jdkgroup.utils.AppUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CustomerDetailsFragment extends BaseFragment implements View.OnClickListener {

    Unbinder unbinder;
    @BindView (R.id.appTvCustomerEdit)
    AppCompatTextView appTvCustomerEdit;

    public CustomerDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_details, null);
        unbinder = ButterKnife.bind(this, view);
        setHasOptionsMenu(true);

        appTvCustomerEdit.setOnClickListener(this);
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
        menu.findItem(R.id.action_lead_opportunity_view).setVisible(false);
        menu.findItem(R.id.action_lead_quotation_view).setVisible(false);
        menu.findItem(R.id.action_lead_invoice_view).setVisible(false);
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
                AppUtils.lauchFramgentActivity(getActivity(), AppConstant.LAUNCH_LEAD_CREATE_BASIC_FRAGMENT);
                return false;

            default:
                break;
        }

        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.appTvCustomerEdit:
                AppUtils.lauchFramgentActivity(getActivity(), AppConstant.LAUNCH_CUSTOMER_CREATE_EDIT_FRAGMENT);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}