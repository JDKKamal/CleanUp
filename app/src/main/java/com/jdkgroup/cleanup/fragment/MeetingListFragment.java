package com.jdkgroup.cleanup.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jdkgroup.baseclasses.BaseFragment;
import com.jdkgroup.cleanup.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MeetingListFragment extends BaseFragment implements View.OnClickListener  {

    Unbinder unbinder;

    public MeetingListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meeting_list, null);
        unbinder = ButterKnife.bind(this, view);

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
        switch (view.getId())
        {

        }
    }

   @Override
    public void onDestroyView() {
       super.onDestroyView();
       unbinder.unbind();
   }
}