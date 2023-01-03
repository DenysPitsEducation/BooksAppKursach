package gm.books.data

import gm.books.domain.entities.FirebaseResponse
import javax.inject.Inject

class CacheDataSource @Inject constructor() {
    var firebaseResponse: FirebaseResponse? = null
}
