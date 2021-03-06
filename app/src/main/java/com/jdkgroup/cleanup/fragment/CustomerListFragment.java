package com.jdkgroup.cleanup.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
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

import com.jdkgroup.baseclasses.MVPFragment;
import com.jdkgroup.cleanup.R;
import com.jdkgroup.cleanup.adapter.CustomerListAdapter;
import com.jdkgroup.constant.AppConstant;
import com.jdkgroup.cleanup.model.User;
import com.jdkgroup.cleanup.model.api.ModelCustomerList;
import com.jdkgroup.presenter.CustomerListPresenter;
import com.jdkgroup.utils.AppUtils;
import com.jdkgroup.view.CustomerListView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static java.util.Arrays.asList;

public class CustomerListFragment extends MVPFragment<CustomerListPresenter, CustomerListView> implements CustomerListView, View.OnClickListener, CustomerListAdapter.ItemListener {

    Unbinder unbinder;
    @BindView (R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @BindView (R.id.recyclerView)
    RecyclerView recyclerView;

    private HashMap<String, String> paramMap;

    public CustomerListFragment() {
    }

    public static CustomerListFragment newInstance(){
        CustomerListFragment fragment = new CustomerListFragment();
        return fragment;
    }

    @NonNull
    @Override
    public CustomerListPresenter createPresenter() {
        return new CustomerListPresenter();
    }

    @NonNull
    @Override
    public CustomerListView attachView() {
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_list, null);
        unbinder = ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        APICall();
        return view;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_search).setVisible(true);
        menu.setGroupVisible(R.id.action_group_customer, true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return false;

            case R.id.action_customer_create:
                AppUtils.lauchFramgentActivity(getActivity(), AppConstant.LAUNCH_CUSTOMER_CREATE_FRAGMENT);
                return true;

            default:
                break;
        }

        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
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
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }

    @RequiresApi (api = Build.VERSION_CODES.N)
    private void setData() {
        CustomerListAdapter customerListAdapter = new CustomerListAdapter(getActivity(), getCustomerList());
        recyclerView.setAdapter(customerListAdapter);
        customerListAdapter.setListener(this);
    }

    @RequiresApi (api = Build.VERSION_CODES.N)
    private List<ModelCustomerList> getCustomerList() {

        List<ModelCustomerList> alCustomerList = asList
                (
                        new ModelCustomerList("1", "", "", "", "", "", "", "", "", "", ""),
                        new ModelCustomerList("2", "", "", "", "", "", "", "", "", "", ""),
                        new ModelCustomerList("1", "", "", "", "", "", "", "", "", "", ""),
                        new ModelCustomerList("2", "", "", "", "", "", "", "", "", "", ""),
                        new ModelCustomerList("1", "", "", "", "", "", "", "", "", "", ""),
                        new ModelCustomerList("2", "", "", "", "", "", "", "", "", "", ""),
                        new ModelCustomerList("1", "", "", "", "", "", "", "", "", "", ""),
                        new ModelCustomerList("2", "", "", "", "", "", "", "", "", "", ""),
                        new ModelCustomerList("1", "", "", "", "", "", "", "", "", "", ""),
                        new ModelCustomerList("2", "", "", "", "", "", "", "", "", "", ""),
                        new ModelCustomerList("1", "", "", "", "", "", "", "", "", "", ""),
                        new ModelCustomerList("2", "", "", "", "", "", "", "", "", "", ""),
                        new ModelCustomerList("1", "", "", "", "", "", "", "", "", "", ""),
                        new ModelCustomerList("2", "", "", "", "", "", "", "", "", "", ""),
                        new ModelCustomerList("1", "", "", "", "", "", "", "", "", "", ""),
                        new ModelCustomerList("2", "", "", "", "", "", "", "", "", "", ""),
                        new ModelCustomerList("1", "", "", "", "", "", "", "", "", "", ""),
                        new ModelCustomerList("2", "", "", "", "", "", "", "", "", "", ""),
                        new ModelCustomerList("1", "", "", "", "", "", "", "", "", "", ""),
                        new ModelCustomerList("2", "", "", "", "", "", "", "", "", "", ""),
                        new ModelCustomerList("3", "", "", "", "", "", "", "", "", "", "")
                );

        //List<ModelCustomerList> olderUsers = modelCustomerList.stream().filter(u -> u.getUid().equalsIgnoreCase("1")).collect(Collectors.toList());
        return alCustomerList;
    }

    @Override
    public void onCustomerDetail(int id, ModelCustomerList modelCustomerList) {
        AppUtils.lauchFramgentActivity(getActivity(), AppConstant.LAUNCH_CUSTOMER_CREATE_DETAILS_FRAGMENT);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void APICall() {
        paramMap = new HashMap();
        paramMap.put("userId", "1");
        getPresenter().callRXJavSingleDetailApi(paramMap);
    }

    @Override
    public void responseCustomerList(User user) {
        showSnakBar(coordinatorLayout, getString(R.string.msg_api_call_successful));
    }

    @Override
    public void onFailure(String message) {

    }
}