package com.jdkgroup.cleanup.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
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
import com.jdkgroup.cleanup.adapter.InvoiceListAdapter;
import com.jdkgroup.cleanup.model.api.ModelLeadList;
import com.jdkgroup.constant.AppConstant;
import com.jdkgroup.utils.AppUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static java.util.Arrays.asList;

public class InvoiceViewFragment extends BaseFragment implements View.OnClickListener {

    Unbinder unbinder;

    public InvoiceViewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_invoice_list, null);
        unbinder = ButterKnife.bind(this, view);
        setHasOptionsMenu(true);

        return view;
    }

    @RequiresApi (api = Build.VERSION_CODES.N)
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
        menu.setGroupVisible(R.id.action_group_invoice, true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_invoice_quotation_view:

                return false;

            case R.id.action_invoice_task_view:

                return false;

            case R.id.action_invoice_subtask_task_view:

                return false;

            case R.id.action_invoice_meeting_task_view:

                return false;

            case R.id.action_invoice_save:

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}