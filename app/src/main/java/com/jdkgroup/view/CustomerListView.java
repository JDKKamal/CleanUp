package com.jdkgroup.view;

import com.jdkgroup.baseclasses.BaseView;
import com.jdkgroup.cleanup.model.User;

public interface CustomerListView extends BaseView<User> {
    void responseCustomerList(User response);
}

