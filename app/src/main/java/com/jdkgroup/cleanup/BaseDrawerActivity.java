package com.jdkgroup.cleanup;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.jdkgroup.baseclasses.BaseActivity;
import com.jdkgroup.cleanup.activity.ProfileEditActivity;
import com.jdkgroup.constant.AppConstant;
import com.jdkgroup.customviews.FontTypeface;
import com.jdkgroup.utils.AppUtils;

import java.util.Stack;

import butterknife.BindView;

public class BaseDrawerActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, FragmentManager.OnBackStackChangedListener {

    @BindView (R.id.drawer_layout)
    public DrawerLayout drawerLayout;
    @BindView (R.id.navigationView)
    NavigationView navigationView;
    @BindView (R.id.toolBar)
    Toolbar toolBar;

    public AppCompatImageView appIvProfile;
    public AppCompatTextView appTvName, appTvEmail;
    private int openDrawer = 1, closeDrawer = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation);

        hideSoftKeyboard();
        bindViews();
        setupHeader();

        setSupportActionBar(toolBar);
        toolBarSetFont(toolBar);

        actionBarDrawerToggle(drawerLayout, toolBar);

        fontSetNavigationMenu(navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        appIvProfile.setBackgroundResource(R.drawable.iv_profile_placeholder);

        AppUtils.lauchFramgentActivity(getActivity(), AppConstant.LAUNCH_HOME_FRAGMENT);

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Fragment f = getSupportFragmentManager().findFragmentById(R.id.frameLayout);
                if (f != null) {
                    updateTitleAndDrawer(f);
                }
            }
        });


        appIvProfile.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem item;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                closeDrawerLeft(openDrawer);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupHeader() {
        View headerView = navigationView.getHeaderView(0);
        appIvProfile = headerView.findViewById(R.id.appIvProfile);
        appTvName = headerView.findViewById(R.id.appTvName);
        appTvEmail = headerView.findViewById(R.id.appTvEmail);

        headerView.findViewById(R.id.appIvProfile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGlobalMenuHeaderClick(v);
            }
        });
    }

    public void onGlobalMenuHeaderClick(final View v) {
        closeDrawerLeft(closeDrawer);
    }

    private void closeDrawerLeft(int isDrawer) {
        switch (isDrawer) {
            case 0:
                drawerLayout.closeDrawer(Gravity.LEFT);
                break;

            case 1:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
        }
    }

    @SuppressWarnings ("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle setting view item clicks here.
        launchNavigationMenu(item.getItemId());
        closeDrawerLeft(closeDrawer);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.appIvProfile:
                closeDrawerLeft(closeDrawer);
                launch(ProfileEditActivity.class);
                break;
        }
    }

    private void launchNavigationMenu(final int id) {
        switch (id) {
            case R.id.nav_home:
                AppUtils.lauchFramgentActivity(getActivity(), AppConstant.LAUNCH_HOME_FRAGMENT);
                break;

            case R.id.nav_customer:
                AppUtils.lauchFramgentActivity(getActivity(), AppConstant.LAUNCH_CUSTOMER_LIST_FRAGMENT);
                break;

            case R.id.nav_lead:
                AppUtils.lauchFramgentActivity(getActivity(), AppConstant.LAUNCH_LEAD_LIST_FRAGMENT);
                break;

            case R.id.nav_opportunity:
                AppUtils.lauchFramgentActivity(getActivity(), AppConstant.LAUNCH_OPPORTUNITY_LIST_FRAGMENT);
                break;

            case R.id.nav_quotation:
                AppUtils.lauchFramgentActivity(getActivity(), AppConstant.LAUNCH_QUOTATION_LIST_FRAGMENT);
                break;

            case R.id.nav_invoice:
                AppUtils.lauchFramgentActivity(getActivity(), AppConstant.LAUNCH_INVOICE_LIST_FRAGMENT);
                break;

            case R.id.nav_meeting:
                AppUtils.lauchFramgentActivity(getActivity(), AppConstant.LAUNCH_MEETING_LIST_FRAGMENT);
                break;

            case R.id.nav_task:
                AppUtils.lauchFramgentActivity(getActivity(), AppConstant.LAUNCH_TASK_LIST_FRAGMENT);
                break;
        }
    }

    private void fontSetNavigationMenu(NavigationView navigationView) {
        FontTypeface fontTypeface = new FontTypeface(this);
        Typeface typeface = fontTypeface.getTypefaceAndroid();

        MenuItem item;

        item = navigationView.getMenu().findItem(R.id.nav_home);
        fontSetNavigationMenu(item, typeface);

        item = navigationView.getMenu().findItem(R.id.nav_customer);
        fontSetNavigationMenu(item, typeface);

        item = navigationView.getMenu().findItem(R.id.nav_lead);
        fontSetNavigationMenu(item, typeface);

        item = navigationView.getMenu().findItem(R.id.nav_opportunity);
        fontSetNavigationMenu(item, typeface);

        item = navigationView.getMenu().findItem(R.id.nav_quotation);
        fontSetNavigationMenu(item, typeface);

        item = navigationView.getMenu().findItem(R.id.nav_invoice);
        fontSetNavigationMenu(item, typeface);

        item = navigationView.getMenu().findItem(R.id.nav_meeting);
        fontSetNavigationMenu(item, typeface);

        item = navigationView.getMenu().findItem(R.id.nav_task);
        fontSetNavigationMenu(item, typeface);
    }

    private void updateTitleAndDrawer(Fragment fragment) {
        toolBarSetTitle(fragment.getClass().getName().replace("com.jdkgroup.cleanup.fragment.", "")); //TODO TITLE
    }

    public void toolBarSetTitle(String str) {
        switch (str) {
            case "HomeFragment":
                navigationView.getMenu().getItem(0).setChecked(true);
                toolBar.setTitle(getString(R.string.app_name));
                break;

            case "VerifyOTPFragment":
                navigationView.getMenu().getItem(1).setChecked(true);
                toolBar.setTitle(getString(R.string.title_otp));
                break;

            case "CustomerListFragment":
                navigationView.getMenu().getItem(1).setChecked(true);
                toolBar.setTitle(getString(R.string.action_customer));
                break;

            case "CustomerCreateFragment":
                navigationView.getMenu().getItem(1).setChecked(true);
                toolBar.setTitle(getString(R.string.action_customer_create));
                break;

            case "CustomerDetailsFragment":
                navigationView.getMenu().getItem(1).setChecked(true);
                toolBar.setTitle(getString(R.string.title_customer_details));
                break;

            case "CustomerEditFragment":
                navigationView.getMenu().getItem(1).setChecked(true);
                toolBar.setTitle(getString(R.string.title_customer_edit));
                break;

            case "LeadListFragment":
                navigationView.getMenu().getItem(2).setChecked(true);
                toolBar.setTitle(getString(R.string.action_lead));
                break;

            case "LeadSearchToCreateFragment":
                navigationView.getMenu().getItem(2).setChecked(true);
                toolBar.setTitle(getString(R.string.title_lead_search_to_create));
                break;

            case "LeadCreateFragment":
                navigationView.getMenu().getItem(2).setChecked(true);
                toolBar.setTitle(getString(R.string.action_lead_create));
                break;

            case "LeadCreateBasicFragment":
                navigationView.getMenu().getItem(2).setChecked(true);
                toolBar.setTitle(getString(R.string.action_lead_create));
                break;

            case "LeadDetailsFragment":
                navigationView.getMenu().getItem(2).setChecked(true);
                toolBar.setTitle(getString(R.string.title_lead_details));
                break;

            case "LeadCreateBasicEditFragment":
                navigationView.getMenu().getItem(2).setChecked(true);
                toolBar.setTitle(getString(R.string.title_lead_edit));
                break;

            case "LeadEditFragment":
                navigationView.getMenu().getItem(2).setChecked(true);
                toolBar.setTitle(getString(R.string.title_lead_edit));
                break;

            case "OpportunityListFragment":
                navigationView.getMenu().getItem(3).setChecked(true);
                toolBar.setTitle(getString(R.string.action_opportunity));
                break;

            case "OpportunityDetailsFragment":
                navigationView.getMenu().getItem(3).setChecked(true);
                toolBar.setTitle(getString(R.string.title_opportunity_details));
                break;

            case "QuotationListFragment":
                navigationView.getMenu().getItem(4).setChecked(true);
                toolBar.setTitle(getString(R.string.action_quotation));
                break;

            case "QuotationSearchToCreateFragment":
                navigationView.getMenu().getItem(4).setChecked(true);
                toolBar.setTitle(getString(R.string.title_quotation_search_to_create));
                break;

            case "QuotationDetailsFragment":
                navigationView.getMenu().getItem(4).setChecked(true);
                toolBar.setTitle(getString(R.string.title_quotation_details));
                break;

            case "QuotationCreateFragment":
                navigationView.getMenu().getItem(4).setChecked(true);
                toolBar.setTitle(getString(R.string.title_quotation_create));
                break;

            case "InvoiceListFragment":
                navigationView.getMenu().getItem(5).setChecked(true);
                toolBar.setTitle(getString(R.string.action_invoice));
                break;

            case "MeetingListFragment":
                navigationView.getMenu().getItem(6).setChecked(true);
                toolBar.setTitle(getString(R.string.action_meeting));
                break;

            case "TaskListFragment":
                navigationView.getMenu().getItem(7).setChecked(true);
                toolBar.setTitle(getString(R.string.action_task));
                break;
        }
    }

    @Override
    public void onBackStackChanged() {
        super.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        /*if (className.equals("HomeFragment")) {
            AppUtils.appExist(getActivity());
        } else {
            super.onBackPressed();
        }*/

        FragmentManager manager = ((BaseDrawerActivity) this).getSupportFragmentManager();
        if (manager.getBackStackEntryCount() == 1) {
            AppUtils.appExist(getActivity());
        } else {
            manager.popBackStack();
        }
    }

    private void clearBackStack() {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        while (fragmentManager.getBackStackEntryCount() != 0) {
            fragmentManager.popBackStackImmediate();
        }
    }
}
