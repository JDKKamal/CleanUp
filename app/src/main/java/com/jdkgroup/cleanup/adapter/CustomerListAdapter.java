package com.jdkgroup.cleanup.adapter;

import android.app.Activity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jdkgroup.cleanup.R;
import com.jdkgroup.cleanup.model.api.ModelCustomerList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.ViewHolder> {

    private List<ModelCustomerList> alCustomerList;
    private Activity activity;

    private ItemListener listener;

    public CustomerListAdapter(Activity activity, List<ModelCustomerList> alCustomerList) {
        this.activity = activity;
        this.alCustomerList = alCustomerList;
    }

    public void setListener(ItemListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_customer_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return alCustomerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.appTvView)
        AppCompatTextView appTvView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);

            appTvView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.appTvView:
                    listener.onCustomerDetail(getAdapterPosition(), alCustomerList.get(getAdapterPosition()));
                    break;

            }
        }
    }

    public interface ItemListener {
         void onCustomerDetail(final int id, final ModelCustomerList modelCustomerList);
    }
}