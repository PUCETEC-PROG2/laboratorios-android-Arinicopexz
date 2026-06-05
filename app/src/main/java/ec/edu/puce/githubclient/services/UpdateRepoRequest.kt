package ec.edu.puce.githubclient.services
import com.google.gson.annotations.SerializedName

data class UpdateRepoRequest(
    @SerializedName("name")
    val name: String? = null,

    @SerializedName("description")
    val description: String? = null
)
