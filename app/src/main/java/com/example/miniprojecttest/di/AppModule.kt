package com.example.miniprojecttest.di

import android.content.Context
import com.example.miniprojecttest.Apps
import com.example.miniprojecttest.helper.TagInjection
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module(
    includes = [
        ViewModelModule::class,
        ActivityBuilderModule::class,
        NetworkModule::class,
        ApiModule::class,
        CacheModule::class
    ]
)
class AppModule {

    @Singleton
    @Provides
    fun provideContext(application: Apps): Context {
        return application
    }

    @Named(TagInjection.IO_Scheduler)
    @Provides
    fun provideIoScheduler(): Scheduler = Schedulers.io()

    @Named(TagInjection.UI_Scheduler)
    @Provides
    fun provideUiScheduler(): Scheduler = AndroidSchedulers.mainThread()
}
