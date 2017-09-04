package com.jdkgroup.interacter;


import com.jdkgroup.cleanup.database.RealmController;
import com.jdkgroup.cleanup.model.User;
import com.jdkgroup.cleanup.model.api.CountryModel;
import com.jdkgroup.connection.RestConstant;
import com.jdkgroup.utils.AppUtils;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;

public class AppInteractor {

    public AppInteractor() {
    }

    private void sendResponse(InterActorCallback callback, Response response) {
        callback.onResponse(response);
    }

    private void displayRequestParams(HashMap<String, String> hashMap) {
        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            AppUtils.loge(RestConstant.API_PARAMETER + pair.getKey() + pair.getValue());
        }
    }

    public void callingAPI(HashMap<String, String> paramMap, final InterActorCallback<User> callback) {
        callback.onStart();
        Rx2AndroidNetworking.get(RestConstant.API_GETANUSER)
                .addPathParameter(paramMap)
                .build()
                .getObjectObservable(User.class)
                .subscribeOn(Schedulers.io())
                .observeOn(mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(User user) {
                        sendResponse(callback, user);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e.toString());
                    }

                    @Override
                    public void onComplete() {
                        callback.onFinish();
                    }
                });
    }

    public void getCountryList(RealmController realmController, final InterActorCallback<List<CountryModel>> callback) {
        callback.onStart();
        List<CountryModel> alCountryListObserver = realmController.getAllData();
        Observable<List<CountryModel>> observable = Observable.just(alCountryListObserver);
        Observer<List<CountryModel>> observer = new Observer<List<CountryModel>>() {
            @Override
            public void onError(Throwable e) {
                callback.onError(e.toString());
            }

            @Override
            public void onComplete() {
                callback.onFinish();
            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<CountryModel> response) {
                callback.onResponse(response);
            }
        };

        observable.subscribe(observer);
    }
}

