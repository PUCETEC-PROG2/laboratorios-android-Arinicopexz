package ec.edu.puce.githubclient.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ec.edu.puce.githubclient.models.GithubUser
import ec.edu.puce.githubclient.models.Repository

@Composable
fun RepoItem(repository: Repository) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
        ) {
            AsyncImage(
                // Asegúrate de que 'owner' y 'avatarUrl' no sean nulos en tu modelo
                model = repository.owner.avatarUrl,
                contentDescription = "Imagen de repositorio \"${repository.name}\"",
                modifier = Modifier.size(68.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(width = 16.dp))

            Column {
                Text(
                    text = repository.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                // Corregido: Se accede mediante 'repository.'
                if (!repository.description.isNullOrBlank()) {
                    Spacer(modifier = Modifier.height(height = 4.dp))
                    Text(
                        text = repository.description,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }

                // Corregido: Se accede mediante 'repository.'
                if (!repository.language.isNullOrBlank()) {
                    Spacer(modifier = Modifier.height(height = 4.dp))
                    Text(
                        text = repository.language,
                        style = MaterialTheme.typography.labelSmall,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RepoItemPreview() {
    // Nota: Ajustolos parámetros de Repository y Owner según cómo estén definidos exactamente en tus modelos.
    val repository = Repository(
        id = "12312414",
        name = "Nombre del repositorio",
        description = "Descripción del repositorio",
        language = "Kotlin",
        owner = GithubUser(
            id="213123",
            login = "arielroserotoscano",
            avatarUrl = "https://avatars.githubusercontent.com/u/48026030?v=4"
        )
    )
    RepoItem(repository)
}