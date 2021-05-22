package com.example.miniprojecttest

import com.example.miniprojecttest.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class Apps : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
        appComponent.inject(this)
        return appComponent
    }
}
