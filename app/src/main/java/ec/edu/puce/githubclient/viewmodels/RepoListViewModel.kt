package ec.edu.puce.githubclient.viewmodels

import android.R
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.serialization.serializers.MutableStateFlowSerializer
import ec.edu.puce.githubclient.models.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RepoListViewModel: ViewModel() {
    private val _repos = MutableStateFlow<List<Repository>>(value= emptyList())
    val repos: StateFlow<List<Repository>> = _repos.asStateFlow()

    private val _isloading = MutableStateFlow(value = false)
    val isloading: StateFlow<Boolean> =_isloading.asStateFlow()

    private val _errMsg = MutableStateFlow<string?>(value = null)
    val errMsg: StateFlow<String?> _errMsg.asStateFlow()

    init {
        fetchRepos()
    }

    fun fetchRepos (){
        viewModelScope.launch {
            _isloading.value = null

        }
    }
    }

    }
}