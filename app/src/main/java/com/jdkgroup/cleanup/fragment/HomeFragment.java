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
import com.jdkgroup.cleanup.adapter.HomeAdapter;
import com.jdkgroup.cleanup.model.api.ModelHome;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static java.util.Arrays.asList;

public class HomeFragment extends BaseFragment  {

    Unbinder unbinder;
    @BindView (R.id.recyclerView)
    RecyclerView recyclerView;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

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

    @RequiresApi (api = Build.VERSION_CODES.N)
    private void setData() {
        HomeAdapter homeAdapter = new HomeAdapter(getActivity(), getModuleList());
        recyclerView.setAdapter(homeAdapter);
    }

    @RequiresApi (api = Build.VERSION_CODES.N)
    private List<ModelHome> getModuleList() {

        List<ModelHome> modelHome = asList
                (
                        new ModelHome(1, "Lead", 100, 10),
                        new ModelHome(1, "Lead Good", 100, 10),
                        new ModelHome(1, "Lead Lost", 100, 10),
                        new ModelHome(1, "Lead Spam", 100, 10),
                        new ModelHome(1, "Opportunity", 100, 10),
                        new ModelHome(1, "Opportunity In Process", 100, 10),
                        new ModelHome(1, "Opportunity Spam", 100, 10),
                        new ModelHome(1, "Opportunity Lost", 100, 10),
                        new ModelHome(1, "Invoice", 100, 10),
                        new ModelHome(1, "Task", 100, 10),
                        new ModelHome(1, "Task Assign", 100, 10),
                        new ModelHome(1, "Task Unassign", 100, 10)
                );

        return modelHome;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}