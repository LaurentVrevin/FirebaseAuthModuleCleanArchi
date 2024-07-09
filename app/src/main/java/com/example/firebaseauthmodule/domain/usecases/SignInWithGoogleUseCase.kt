package com.example.firebaseauthmodule.domain.usecases

import com.example.firebaseauthmodule.domain.model.User
import com.example.firebaseauthmodule.domain.repository.AuthRepository
import javax.inject.Inject

class SignInWithGoogleUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(idToken: String): Result<User?> {
        return authRepository.signInWithGoogle(idToken)
    }
}