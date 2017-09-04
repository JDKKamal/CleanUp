package com.jdkgroup.view;

import com.jdkgroup.baseclasses.BaseView;
import com.jdkgroup.cleanup.model.User;

import java.util.List;

public interface RegisterUserView extends BaseView<User> {
    void responseCountryList(List<?> response);
}

