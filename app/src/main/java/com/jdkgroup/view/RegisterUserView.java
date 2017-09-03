package com.jdkgroup.view;

import com.jdkgroup.baseclasses.BaseView;
import com.jdkgroup.cleanup.model.User;
import com.jdkgroup.cleanup.model.api.CountryModel;

import java.util.List;

public interface RegisterUserView extends BaseView<User> {
    void responseCountryList(List<CountryModel> response);
}

