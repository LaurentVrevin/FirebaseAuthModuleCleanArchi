package com.example.firebaseauthmodule.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebaseauthmodule.domain.model.User
import com.example.firebaseauthmodule.domain.usecases.SignOutUseCase
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase
) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    fun setUser(user: User?) {
        _user.value = user
    }

    fun signOut(onSignOut: () -> Unit, onError: (Exception) -> Unit) {
        viewModelScope.launch {
            val result = signOutUseCase()
            if (result.isSuccess) {
                _user.value = null
                onSignOut()
            } else {
                val exception = result.exceptionOrNull() ?: Exception("Unknown error")
                onError(exception as Exception)
            }
        }
    }
}