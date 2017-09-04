package com.jdkgroup.presenter;

import com.jdkgroup.baseclasses.BasePresenter;
import com.jdkgroup.cleanup.model.User;
import com.jdkgroup.interacter.InterActorCallback;
import com.jdkgroup.view.CustomerListView;
import java.util.HashMap;
import java.util.List;

public class CustomerListPresenter extends BasePresenter<CustomerListView> {

    public void callRXJavSingleDetailApi(HashMap<String, String> paramMap) {
        getAppInteractor().callingAPI(paramMap, new InterActorCallback<User>() {
            @Override
            public void onStart() {
                getView().showProgressDialog(true);
            }

            @Override
            public void onResponse(User response) {
                getView().responseCustomerList(response);
            }

            @Override
            public void onFinish() {
                getView().showProgressDialog(false);
            }

            @Override
            public void onError(String message) {
                getView().onFailure(message);
            }
        });
    }
}