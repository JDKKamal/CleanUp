package com.jdkgroup.cleanup.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
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

public class LeadEditFragment extends BaseFragment implements View.OnClickListener {

    Unbinder unbinder;
    @BindView (R.id.appBtnSubmit)
    AppCompatButton appBtnSubmit;

    public LeadEditFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lead_create, null);
        unbinder = ButterKnife.bind(this, view);
        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        appBtnSubmit.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action).setVisible(true);
        menu.setGroupVisible(R.id.action_group_customer, false);
        menu.setGroupVisible(R.id.action_group_lead, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action:
                AppUtils.hideKeyboard(getActivity());
                AppUtils.lauchFramgentActivity(getActivity(), AppConstant.LAUNCH_LEAD_LIST_FRAGMENT);
                return false;

            default:
                break;
        }

        return false;
    }

    @Override
    public void onClick(View view) {
       switch (view.getId())
       {
           case R.id.appBtnSubmit:
               AppUtils.lauchFramgentActivity(getActivity(), AppConstant.LAUNCH_LEAD_LIST_FRAGMENT);
               break;
       }
    }

   @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}