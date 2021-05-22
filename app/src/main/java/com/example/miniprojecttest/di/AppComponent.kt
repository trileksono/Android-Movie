package com.example.miniprojecttest.di

import com.example.miniprojecttest.Apps
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class
    ]
)

interface AppComponent : AndroidInjector<Apps> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Apps): Builder

        fun build(): AppComponent
    }
}
