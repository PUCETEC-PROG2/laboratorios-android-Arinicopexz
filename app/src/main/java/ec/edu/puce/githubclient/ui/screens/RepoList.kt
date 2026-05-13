package ec.edu.puce.githubclient.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ec.edu.puce.githubclient.ui.components.RepoItem

@Preview(showBackground = true)
@Composable
fun RepoList(
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
    ) {
        RepoItem(
            name = "Repositorio de Android",
            description = "Repositorio creado en kotlin",
            avatarUrl = "https://marketing4ecommerce.co/wp-content/uploads/2019/09/imagen-vectorial-compressor.jpg",
            language = "Kotlin"
        )
        RepoItem(
            name = "Repositorio de Android",
            description = "Repositorio creado en kotlin",
            avatarUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRjALFFJ_6U9dRONxmPrrjcmDSqAAzQnZ5evQ&s",
            language = "Python"
        )
        RepoItem(
            name = "Repositorio de Android",
            description = "Repositorio creado en kotlin",
            avatarUrl = "https://img.freepik.com/fotos-premium/patron-rayos-solares-radiantes-colores-negrita-imagen-fondo-patron-papel-pared-patron_1020697-727366.jpg?semt=ais_hybrid&w=740&q=80",
            language = "C++"
        )
        RepoItem(
            name = "Repositorio de Android",
            description = "Repositorio creado en kotlin",
            avatarUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQDbxcSwXhWTETr7a-bPWWEA2DHEcvEgOa4jQ&s",
            language = "Ruby"
        )
        RepoItem(
            name = "Repositorio de Android",
            description = "Repositorio creado en kotlin",
            avatarUrl = "https://images.unsplash.com/photo-1689308271305-58e75832289b?fm=jpg&q=60&w=3000&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            language = "Javascipt"
        )
    }
}