package com.jdkgroup.cleanup

import android.os.Bundle
import android.view.View
import com.jdkgroup.baseclasses.BaseActivity
import com.jdkgroup.utils.AppUtils
import java.util.ArrayList

class MainActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppUtils.getDeviceInfo(this);
    }

    override fun onClick(view: View) {

    }
}
