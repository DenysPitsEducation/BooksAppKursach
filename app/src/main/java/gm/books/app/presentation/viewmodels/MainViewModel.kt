package gm.books.app.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import gm.books.app.presentation.adapters.Genre
import gm.books.domain.entities.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {

    lateinit var genres: List<Genre>

    private var progressValue = 0
    private val timeInterval: Long = 2000 / 100

    val progress: LiveData<Int> = liveData(Dispatchers.IO) {
        while (progressValue != 101) {
            emit(progressValue)
            progressValue++
            delay(timeInterval)
        }
    }

    fun refreshGenres(books: List<Book>) {
        genres = convertBooksToGenres(books)
    }

    private fun convertBooksToGenres(books: List<Book>): List<Genre> {
        return books.groupBy { it.genre }.map { Genre(it.key, it.value) }
    }
}