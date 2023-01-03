package gm.books.domain.usecases

import gm.books.domain.abstractions.BooksRepository
import gm.books.domain.entities.FirebaseResponse
import javax.inject.Inject

class GetFirebaseResponse @Inject constructor(private val booksRepository: BooksRepository) {
    suspend operator fun invoke(): FirebaseResponse {
        return booksRepository.getFirebaseResponse()
    }
}