package gm.books.domain.abstractions

import gm.books.domain.entities.FirebaseResponse

interface BooksRepository {

    suspend fun getFirebaseResponse(): FirebaseResponse
}