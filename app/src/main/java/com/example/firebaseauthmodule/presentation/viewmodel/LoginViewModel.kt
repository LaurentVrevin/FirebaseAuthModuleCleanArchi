package com.example.firebaseauthmodule.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebaseauthmodule.domain.model.User
import com.example.firebaseauthmodule.domain.repository.AuthRepository
import com.example.firebaseauthmodule.util.SignInException
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val auth: FirebaseAuth
) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    init {
        checkUserStatus()
    }

    private fun checkUserStatus() {
        viewModelScope.launch {
            val currentUser = auth.currentUser
            _user.value = currentUser?.let {
                User(
                    uid = it.uid,
                    displayName = it.displayName,
                    email = it.email,
                    photoUrl = it.photoUrl?.toString()
                )
            }
        }
    }

    fun signInWithGoogle(
        idToken: String,
        onSignInSuccess: (User?) -> Unit,
        onSignInError: (Exception) -> Unit
    ) {
        viewModelScope.launch {
            val result = authRepository.signInWithGoogle(idToken)
            result.fold(
                onSuccess = { user ->
                    _user.value = user
                    onSignInSuccess(user)
                },
                onFailure = { e ->
                    val errorMessage = when (e) {
                        is java.net.UnknownHostException -> "Problème de réseau, veuillez réessayer."
                        is ApiException -> "Échec de la connexion avec Google."
                        is FirebaseAuthException -> "Échec de l'authentification Firebase."
                        else -> "Une erreur inconnue est survenue."
                    }
                    onSignInError(SignInException(errorMessage))
                }
            )
        }
    }

}