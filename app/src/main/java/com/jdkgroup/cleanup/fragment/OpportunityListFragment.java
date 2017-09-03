package com.jdkgroup.cleanup.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jdkgroup.baseclasses.BaseFragment;
import com.jdkgroup.cleanup.R;
import com.jdkgroup.cleanup.adapter.OpportunityListAdapter;
import com.jdkgroup.constant.AppConstant;
import com.jdkgroup.cleanup.model.api.ModelLeadList;
import com.jdkgroup.utils.AppUtils;

import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static java.util.Arrays.asList;

public class OpportunityListFragment extends BaseFragment implements  OpportunityListAdapter.ItemListener {

    Unbinder unbinder;
    @BindView (R.id.recyclerView)
    RecyclerView recyclerView;

    public OpportunityListFragment() {
    }

    @RequiresApi (api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_opportunity_list, null);
        unbinder = ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        setData();

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

    @RequiresApi (api = Build.VERSION_CODES.N)
    private void setData() {
        OpportunityListAdapter opportunityListAdapter = new OpportunityListAdapter(getActivity(), getLeadList());
        recyclerView.setAdapter(opportunityListAdapter);
        opportunityListAdapter.setListener(this);
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
        AppUtils.lauchFramgentActivity(getActivity(), AppConstant.LAUNCH_OPPORTUNITY_DETAILS_FRAGMENT);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}