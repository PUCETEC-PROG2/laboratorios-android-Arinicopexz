package ec.edu.puce.githubclient.Models

data class RepositoryPayload(
    val name: String,
    val description: String?,
    val private: Boolean = false
)
