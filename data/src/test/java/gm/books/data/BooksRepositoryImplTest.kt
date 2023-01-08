package gm.books.data

import com.google.common.truth.Truth
import gm.books.domain.abstractions.BooksRepository
import gm.books.domain.entities.FirebaseResponse
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class BooksRepositoryImplTest {

    private val cacheDataSource: CacheDataSource = CacheDataSource()
    private val remoteDataSource: RemoteDataSource = mock()
    private val repository: BooksRepository = BooksRepositoryImpl(cacheDataSource, remoteDataSource)

    @Before
    fun setup() {
        whenever(remoteDataSource.getFirebaseResponse()).thenReturn(
            FirebaseResponse(
                books = listOf(),
                banners = listOf(),
                likeIds = listOf(2)
            )
        )
    }

    @Test
    fun `getFirebaseResponse returns cached response if cache exists`(): Unit = runBlocking {
        cacheDataSource.firebaseResponse = FirebaseResponse(
            books = listOf(),
            banners = listOf(),
            likeIds = listOf(1)
        )
        val result = repository.getFirebaseResponse()
        Truth.assertThat(result.likeIds).contains(1)
    }

    @Test
    fun `getFirebaseResponse returns remote response if cache does not exist`(): Unit = runBlocking {
        cacheDataSource.firebaseResponse = null
        val result = repository.getFirebaseResponse()
        Truth.assertThat(result.likeIds).contains(2)
    }
}