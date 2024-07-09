package com.example.firebaseauthmodule.domain.repository


import com.example.firebaseauthmodule.domain.model.User

interface AuthRepository {
    suspend fun signInWithGoogle(idToken: String): Result<User?>
    suspend fun signOut(): Result<Unit>
}