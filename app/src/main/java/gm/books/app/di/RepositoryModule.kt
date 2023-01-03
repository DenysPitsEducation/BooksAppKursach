package gm.books.app.di

import dagger.Binds
import dagger.Module
import gm.books.data.BooksRepositoryImpl
import gm.books.domain.abstractions.BooksRepository

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideStorage(booksRepository: BooksRepositoryImpl): BooksRepository
}