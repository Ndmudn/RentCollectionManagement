package com.ahpalace.rentmanager.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val auth: FirebaseAuth) : ViewModel() {

    fun sendOtp(phone: String) {
        viewModelScope.launch {
            // TODO: Use PhoneAuthProvider to start phone verification
        }
    }

    fun verifyOtp(otp: String) {
        viewModelScope.launch {
            // TODO: Verify OTP with FirebaseAuth
        }
    }

    fun signInWithEmail(email: String, password: String) {
        viewModelScope.launch {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    // TODO: handle sign-in result
                }
        }
    }
}
