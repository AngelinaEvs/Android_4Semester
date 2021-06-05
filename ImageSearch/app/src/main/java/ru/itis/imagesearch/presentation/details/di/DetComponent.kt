package ru.itis.imagesearch.presentation.details.di

import androidx.fragment.app.Fragment
import dagger.BindsInstance
import dagger.Subcomponent
import ru.itis.imagesearch.presentation.details.DetailsFragment
import ru.itis.imagesearch.presentation.di.ScreenScope
import ru.itis.regme.presenter.calendar.di.DetModule

@Subcomponent(
        modules = [DetModule::class]
)
@ScreenScope
interface DetComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(
                @BindsInstance fragment: Fragment
        ): DetComponent
    }

    fun inject(detailsFragment: DetailsFragment)

}