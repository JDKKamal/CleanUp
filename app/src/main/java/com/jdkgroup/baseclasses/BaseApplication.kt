package com.jdkgroup.baseclasses

import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication

import com.jdkgroup.cleanup.R
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

class BaseApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        baseApplication = this

        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/aileron_regular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build())

        /*   Realm.init(this);
        RealmConfiguration cfg = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(cfg);*/
    }

    companion object {

        var baseApplication: BaseApplication? = null
            private set
    }
}
