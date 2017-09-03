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
import com.jdkgroup.cleanup.adapter.InvoiceListAdapter;
import com.jdkgroup.cleanup.model.api.ModelLeadList;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static java.util.Arrays.asList;

public class InvoiceListFragment extends BaseFragment implements View.OnClickListener  {

    Unbinder unbinder;
    @BindView (R.id.recyclerView)
    RecyclerView recyclerView;

    public InvoiceListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_invoice_list, null);
        unbinder = ButterKnife.bind(this, view);


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
    public void onClick(View view) {
        switch (view.getId())
        {
        }
    }

    @RequiresApi (api = Build.VERSION_CODES.N)
    private void setData() {
        InvoiceListAdapter invoiceListAdapter = new InvoiceListAdapter(getActivity(), getLeadList());
        recyclerView.setAdapter(invoiceListAdapter);
    }

    @RequiresApi (api = Build.VERSION_CODES.N)
    private List<ModelLeadList> getLeadList() {

        List<ModelLeadList> modelCustomerList = asList
                (
                        new ModelLeadList("1", "", "", "", "", "", "", "", "", "", ""),
                        new ModelLeadList("2", "", "", "", "", "", "", "", "", "", ""),
                        new ModelLeadList("3", "", "", "", "", "", "", "", "", "", "")
                );
//lambda here! listDevs.sort((Developer o1, Developer o2)->o1.getAge()-o2.getAge()); listDevs.forEach((developer)->System.out.println(developer));
        List<ModelLeadList> olderUsers = modelCustomerList.stream().filter(u -> u.getUid().equalsIgnoreCase("1")).collect(Collectors.toList());
//listDevs.sort((o1, o2)->o1.getAge()-o2.getAge());

        modelCustomerList.sort(
                Comparator.comparing(ModelLeadList::getCity)
                        .thenComparing(ModelLeadList::getEmail));
        modelCustomerList.sort((o1, o2)->o1.getFirstname().compareTo(o2.getFirstname()));
        return olderUsers;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}