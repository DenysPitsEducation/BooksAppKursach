package gm.books.domain.entities

import com.google.gson.annotations.SerializedName

data class Book(
    val id: Int,
    @SerializedName("name") val title: String,
    val author: String,
    val summary: String,
    val genre: String,
    @SerializedName("cover_url") val coverUrl: String,
    val views: String,
    val likes: String,
    val quotes: String
)