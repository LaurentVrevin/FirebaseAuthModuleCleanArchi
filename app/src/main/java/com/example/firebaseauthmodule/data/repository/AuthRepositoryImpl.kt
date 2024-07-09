package com.example.firebaseauthmodule.data.repository

import com.example.firebaseauthmodule.domain.model.User
import com.example.firebaseauthmodule.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl(
    private val auth: FirebaseAuth, // Firebase authentication instance
    private val googleSignInClient: GoogleSignInClient // Google sign-in client
) : AuthRepository {

    // Sign in with Google
    override suspend fun signInWithGoogle(idToken: String): Result<User?> {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        return try {
            val authResult = auth.signInWithCredential(credential).await() // Await the sign-in result
            val user = authResult.user
            Result.success(user?.let {
                User(
                    uid = it.uid,
                    displayName = it.displayName,
                    email = it.email,
                    photoUrl = it.photoUrl?.toString()
                )
            })
        } catch (e: Exception) {
            Result.failure(e) // Return failure result on error
        }
    }

    // Sign out
    override suspend fun signOut(): Result<Unit> {
        return try {
            auth.signOut() // Sign out from Firebase
            googleSignInClient.signOut().await() // Sign out from Google
            Result.success(Unit) // Return success result
        } catch (e: Exception) {
            Result.failure(e) // Return failure result on error
        }
    }
}


