package gm.books.app.presentation

import android.app.Application
import gm.books.app.di.ApplicationComponent

class BooksApplication : Application() {

    val appComponent: ApplicationComponent by lazy {
        gm.books.app.di.DaggerApplicationComponent.create()
    }
}