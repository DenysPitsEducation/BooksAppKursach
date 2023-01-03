package gm.books.app.di

import dagger.Component
import gm.books.app.di.viewmodel.ViewModelModule
import gm.books.app.presentation.activities.MainActivity
import gm.books.app.presentation.fragments.DetailsFragment
import gm.books.app.presentation.fragments.MainFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(fragment: MainFragment)
    fun inject(fragment: DetailsFragment)
    fun inject(activity: MainActivity)
}