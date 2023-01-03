package gm.books.domain.entities

import com.google.gson.annotations.SerializedName

data class FirebaseResponse(
    @SerializedName("books") val books: List<Book>,
    @SerializedName("top_banner_slides") val banners: List<Banner>,
    @SerializedName("you_will_like_section") val likeIds: List<Int>
)