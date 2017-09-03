package com.jdkgroup.presenter;

import com.jdkgroup.baseclasses.BasePresenter;
import com.jdkgroup.cleanup.R;
import com.jdkgroup.connection.RestConstant;
import com.jdkgroup.cleanup.model.User;
import com.jdkgroup.view.CustomerListView;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CustomerListPresenter extends BasePresenter<CustomerListView> {

    public void callRXJavSingleDetailApi(HashMap<String, String>  paramMap) {
        if (hasInternet()) {
            getView().showProgressDialog(true);
            Rx2AndroidNetworking.get(RestConstant.API_GETANUSER)
                    .addPathParameter(paramMap)
                    .build()
                    .getObjectObservable(User.class)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<User>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(User user) {
                            getView().responseCustomerList(user);
                        }

                        @Override
                        public void onError(Throwable e) {
                            getView().showProgressDialog(false);
                            getView().onFailure(e.toString());
                        }

                        @Override
                        public void onComplete() {
                            getView().showProgressDialog(false);
                        }
                    });
        } else {
            getView().showProgressDialog(false);
            getView().onFailure(getView().getActivity().getString(R.string.no_internet_message));
        }
    }
}