package ec.edu.puce.githubclient.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import ec.edu.puce.githubclient.Models.Repository
import ec.edu.puce.githubclient.services.RetrofitClient
import ec.edu.puce.githubclient.services.UpdateRepoRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RepoListViewModel : ViewModel() {
    private val _repos = MutableStateFlow<List<Repository>>( value = emptyList())
    val repos: StateFlow<List<Repository>> = _repos.asStateFlow()

    private val _isLoading = MutableStateFlow( value = false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMsg = MutableStateFlow<String?>( value = null)
    val errMsg: StateFlow<String?> = _errorMsg.asStateFlow()

    init {
        fetchRepos()
    }

    fun fetchRepos () {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMsg.value = null
            try {
                _repos.value = RetrofitClient.apiService.getRepositories()
            } catch (e: Exception) {
                _errorMsg.value = "Error al cargar repositorios: ${e.localizedMessage}"
                e.printStackTrace()
            }  finally {
                _isLoading.value = false
            }
        }
    }

    //  NUEVA FUNCIÓN: ACTUALIZAR
    fun updateRepo(owner: String, repoName: String, newName: String, newDescription: String) {
        viewModelScope.launch {
            _isLoading.value = true // Prendemos el circulito de carga
            _errorMsg.value = null

            try {
                // Preparamos el formulario (el paquetito de datos)
                val bodyRequest = UpdateRepoRequest(
                    name = newName.ifBlank { null },
                    description = newDescription.ifBlank { null }
                )

                // Mandamos al asistente a hacer la actualización
                RetrofitClient.apiService.updateRepository(
                    owner = owner,
                    repoName = repoName, // El nombre viejo para que sepa cuál buscar
                    body = bodyRequest
                )

                // Si llegamos aquí, fue un éxito. ¡Refrescamos la lista!
                fetchRepos()

            } catch (e: Exception) {
                _errorMsg.value = "Error al actualizar: ${e.localizedMessage}"
                e.printStackTrace()
            } finally {
                _isLoading.value = false // Apagamos el circulito de carga
            }
        }
    }

    //  NUEVA FUNCIÓN: ELIMINAR
    fun deleteRepo(owner: String, repoName: String) {
        viewModelScope.launch {
            _isLoading.value = true // Prendemos el circulito de carga
            _errorMsg.value = null

            try {
                // Mandamos al asistente a destruir la carpeta
                RetrofitClient.apiService.deleteRepository(
                    owner = owner,
                    repoName = repoName
                )

                // Si llegamos aquí, ya se borró. ¡Refrescamos la lista!
                fetchRepos()

            } catch (e: Exception) {
                _errorMsg.value = "Error al eliminar: ${e.localizedMessage}"
                e.printStackTrace()
            } finally {
                _isLoading.value = false // Apagamos el circulito de carga
            }
        }
    }
}