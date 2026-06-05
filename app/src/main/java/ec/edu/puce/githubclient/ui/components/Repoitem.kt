package ec.edu.puce.githubclient.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ec.edu.puce.githubclient.Models.GithubUser
import ec.edu.puce.githubclient.Models.Repository
import ec.edu.puce.githubclient.ui.theme.GithubClientTheme

@Composable
fun RepoItem (
    repository: Repository,
    onUpdate: (String, String) -> Unit,   // <- NUEVO: Función para enviar los cambios
    onDelete: () -> Unit                  // <- NUEVO: Función para avisar que se elimina
) {
    // --- ESTADOS PARA LOS DIÁLOGOS Y TEXTOS (NUEVO) ---
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }
    var newName by remember { mutableStateOf(repository.name) }
    var newDescription by remember { mutableStateOf(repository.description ?: "") }

    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ){
            // 1. Tu imagen original de Coil
            AsyncImage(
                model = repository.owner.avatarUrl,
                contentDescription = "Imagen de \"${repository.name}\"",
                modifier = Modifier.size(size = 60.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(width = 16.dp))

            // 2. Columna con tus textos originales
            // NOTA: Le agregué modifier = Modifier.weight(1f) para que los textos
            // ocupen el espacio disponible y empujen los botones hacia la derecha.
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = repository.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(height = 4.dp))

                if (!repository.description.isNullOrEmpty()) {
                    Text(
                        text = repository.description,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
                Spacer(modifier = Modifier.height(height = 4.dp))

                if (!repository.language.isNullOrEmpty()) {
                    Text(
                        text = repository.language,
                        style = MaterialTheme.typography.labelSmall,
                    )
                }
            }

            // 3. Los botones de acción (NUEVO)
            Row {
                IconButton(onClick = { showEditDialog = true }) {
                    Icon(Icons.Default.Edit, contentDescription = "Editar")
                }
                IconButton(onClick = { showDeleteDialog = true }) {
                    Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = MaterialTheme.colorScheme.error)
                }
            }
        }
    }

    // --- DIÁLOGO DE CONFIRMACIÓN PARA ELIMINAR (NUEVO) ---
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("¿Eliminar repositorio?") },
            text = { Text("Esta acción no se puede deshacer. ¿Seguro que quieres eliminar '${repository.name}'?") },
            confirmButton = {
                TextButton(onClick = {
                    onDelete()
                    showDeleteDialog = false
                }) {
                    Text("Eliminar", color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

    // --- DIÁLOGO PARA EDITAR (NUEVO) ---
    if (showEditDialog) {
        AlertDialog(
            onDismissRequest = { showEditDialog = false },
            title = { Text("Editar repositorio") },
            text = {
                Column {
                    OutlinedTextField(
                        value = newName,
                        onValueChange = { newName = it },
                        label = { Text("Nombre") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = newDescription,
                        onValueChange = { newDescription = it },
                        label = { Text("Descripción") }
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    onUpdate(newName, newDescription)
                    showEditDialog = false
                }) {
                    Text("Guardar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showEditDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RepoItemPreview () {
    GithubClientTheme {
        val repository = Repository(
            id = "12312414",
            name = "Nombre del repositorio",
            description = "Descripcion del repositorio",
            language = "Kotlin",
            owner = GithubUser(
                id = "213123",
                login = "pabloperezmartinez",
                avatarUrl = "https://avatars.githubusercontent.com/u/48026030?v=4"
            )
        )
        // Actualizamos el Preview para que no dé error con las nuevas funciones
        RepoItem(
            repository = repository,
            onUpdate = { _, _ -> },
            onDelete = {}
        )
    }
}