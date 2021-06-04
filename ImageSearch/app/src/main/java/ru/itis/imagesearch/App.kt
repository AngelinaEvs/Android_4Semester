package ru.itis.imagesearch

import android.app.Application
import ru.itis.imagesearch.presentation.di.AppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
                .builder()
                .application(this)
                .build()
    }

}