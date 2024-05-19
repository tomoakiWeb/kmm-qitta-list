import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val name: String,
    @SerialName("profile_image_url") val profileImageUrl: String
)

@Serializable
data class Article(
    val id: String,
    val title: String,
    val body: String,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
    val user: User
)
