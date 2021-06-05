package ru.itis.imagesearch.presentation.di

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import dagger.BindsInstance
import dagger.Component
import ru.itis.imagesearch.data.di.DBModule
import ru.itis.imagesearch.data.di.RepoModule
import ru.itis.imagesearch.presentation.details.DetailsViewModel
import ru.itis.imagesearch.presentation.details.di.DetComponent
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        RepoModule::class,
        DBModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Context): Builder

        fun build(): AppComponent
    }

    fun detFactory(): DetComponent.Factory

}