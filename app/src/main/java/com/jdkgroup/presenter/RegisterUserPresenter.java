package com.jdkgroup.presenter;

import com.jdkgroup.baseclasses.BasePresenter;
import com.jdkgroup.cleanup.database.RealmController;
import com.jdkgroup.cleanup.model.api.CountryModel;
import com.jdkgroup.interacter.InterActorCallback;
import com.jdkgroup.view.RegisterUserView;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RegisterUserPresenter extends BasePresenter<RegisterUserView> {

    public void getCountryList(RealmController realmController) {
        getAppInteractor().getCountryList(realmController, new InterActorCallback<List<CountryModel>>() {
            @Override
            public void onStart() {
                getView().showProgressDialog(true);
            }

            @Override
            public void onResponse(List<CountryModel> response) {
                getView().responseCountryList(response);
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