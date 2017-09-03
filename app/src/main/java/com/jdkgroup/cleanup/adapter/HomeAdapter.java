package com.jdkgroup.cleanup.adapter;

import android.app.Activity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jdkgroup.cleanup.R;
import com.jdkgroup.cleanup.model.api.ModelHome;
import com.jdkgroup.cleanup.model.api.ModelLeadList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private List<ModelHome> alModelList;
    private Activity activity;

    private ItemListener listener;

    public HomeAdapter(Activity activity, List<ModelHome> alModelList) {
        this.activity = activity;
        this.alModelList = alModelList;
    }

    public void setListener(ItemListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_home, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ModelHome modelHome = alModelList.get(position);
        holder.appTvModuleName.setText(String.valueOf(modelHome.getModulename()));
        holder.appTvTotal.setText(activity.getString(R.string.total) + String.valueOf(modelHome.getTotal() + " (" + modelHome.getMytotal()) + ")");
    }

    @Override
    public int getItemCount() {
        return alModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView (R.id.appTvModuleName)
        AppCompatTextView appTvModuleName;

        @BindView (R.id.appTvTotal)
        AppCompatTextView appTvTotal;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface ItemListener {
        void onCustomerDetail(final int id, final ModelLeadList modelLeadList);
    }
}