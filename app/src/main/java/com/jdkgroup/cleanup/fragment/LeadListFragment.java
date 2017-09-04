package com.jdkgroup.cleanup.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.jdkgroup.baseclasses.BaseFragment;
import com.jdkgroup.cleanup.R;
import com.jdkgroup.cleanup.adapter.LeadListAdapter;
import com.jdkgroup.constant.AppConstant;
import com.jdkgroup.cleanup.model.api.ModelLeadList;
import com.jdkgroup.utils.AppUtils;

import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static java.util.Arrays.asList;

public class LeadListFragment extends BaseFragment implements View.OnClickListener, LeadListAdapter.ItemListener {

    Unbinder unbinder;
    @BindView (R.id.recyclerView)
    RecyclerView recyclerView;

    public LeadListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lead_list, null);
        unbinder = ButterKnife.bind(this, view);
        setHasOptionsMenu(true);

        return view;
    }

    @RequiresApi (api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        setData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_search).setVisible(true);
        menu.findItem(R.id.action).setVisible(false);
        menu.setGroupVisible(R.id.action_group_customer, false);
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
        }
    }

    @RequiresApi (api = Build.VERSION_CODES.N)
    private void setData() {
        LeadListAdapter leadListAdapter = new LeadListAdapter(getActivity(), getLeadList());
        recyclerView.setAdapter(leadListAdapter);
        leadListAdapter.setListener(this);
    }

    @RequiresApi (api = Build.VERSION_CODES.N)
    private List<ModelLeadList> getLeadList() {

        List<ModelLeadList> modelCustomerList = asList
                (
                        new ModelLeadList("1", "", "", "", "", "", "", "", "", "", ""),
                        new ModelLeadList("2", "", "", "", "", "", "", "", "", "", ""),
                        new ModelLeadList("3", "", "", "", "", "", "", "", "", "", "")
                );

        List<ModelLeadList> olderUsers = modelCustomerList.stream().filter(u -> u.getUid().equalsIgnoreCase("1")).collect(Collectors.toList());
        return olderUsers;
    }

    @Override
    public void onCustomerDetail(int id, ModelLeadList modelLeadList) {
        AppUtils.lauchFramgentActivity(getActivity(), AppConstant.LAUNCH_LEAD_DETAILS);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}