package gm.books.data

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.gson.Gson
import gm.books.domain.entities.FirebaseResponse
import javax.inject.Inject

class RemoteDataSource @Inject constructor() {
    private val remoteConfig = FirebaseRemoteConfig.getInstance()

    fun getFirebaseResponse(): FirebaseResponse {
        val gson = Gson()
        val json = remoteConfig.getString("json_data")
        return gson.fromJson(json, FirebaseResponse::class.java)
    }
}