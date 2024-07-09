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
    private val signOutUseCase: SignOutUseCase // Inject the SignOutUseCase
) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null) // Private mutable state for user
    val user: StateFlow<User?> = _user // Public read-only state for user

    // Set the current user
    fun setUser(user: User?) {
        _user.value = user
    }

    // Sign out the user
    fun signOut(onSignOut: () -> Unit) {
        viewModelScope.launch {
            val result = signOutUseCase() // Call the SignOutUseCase
            if (result.isSuccess) {
                _user.value = null // Clear the user state
                onSignOut() // Execute the sign-out callback
            } else {
                // Handle error if needed
            }
        }
    }
}