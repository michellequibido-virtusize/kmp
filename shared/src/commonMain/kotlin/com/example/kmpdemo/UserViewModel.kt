package com.example.kmpdemo

import com.example.kmpdemo.model.User
import com.example.kmpdemo.network.APIService
import com.example.kmpdemo.network.provideHttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel {
    private val apiService: APIService = APIService(provideHttpClient())

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users.asStateFlow()

    private val viewModelScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    fun loadUsers() {
        viewModelScope.launch {
            try {
                val data = apiService.getUsers()
                _users.value = data.items
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}