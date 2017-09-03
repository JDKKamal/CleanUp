package com.jdkgroup.presenter;

import com.jdkgroup.baseclasses.BasePresenter;
import com.jdkgroup.cleanup.R;
import com.jdkgroup.cleanup.database.RealmController;
import com.jdkgroup.cleanup.model.User;
import com.jdkgroup.cleanup.model.api.CountryModel;
import com.jdkgroup.connection.RestConstant;
import com.jdkgroup.view.CustomerListView;
import com.jdkgroup.view.RegisterUserView;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterUserPresenter extends BasePresenter<RegisterUserView> {

    public void getCountryList(RealmController realmController) {
        getView().showProgressDialog(true);
        List<CountryModel> alCountryListObserver = realmController.getAllData();

        Observable<List<CoutryModel>> observable = Observable.just(alCountryListObserver);
        Observer<List<CountryModel>> observer = new Observer<List<CountryModel>>() {
            @Override
            public void onError(Throwable e) {
                getView().showProgressDialog(false);
            }

            @Override
            public void onComplete() {
                getView().showProgressDialog(false);
            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<CountryModel> response) {
                getView().responseCountryList(response);
            }
        };

        observable.subscribe(observer);
    }
}