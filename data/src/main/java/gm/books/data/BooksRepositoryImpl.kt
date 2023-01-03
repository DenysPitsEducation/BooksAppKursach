package gm.books.data

import gm.books.domain.abstractions.BooksRepository
import gm.books.domain.entities.FirebaseResponse
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val cacheDataSource: CacheDataSource,
    private val remoteDataSource: RemoteDataSource
) : BooksRepository {
    override suspend fun getFirebaseResponse(): FirebaseResponse {
        val cachedFirebaseResponse = cacheDataSource.firebaseResponse
        return cachedFirebaseResponse ?: remoteDataSource.getFirebaseResponse()
    }
}