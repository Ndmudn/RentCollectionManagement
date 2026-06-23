package com.ahpalace.rentmanager.ui.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController, viewModel: AuthViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Login")
        OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text("+91 Phone") })
        Button(onClick = {
            // send OTP via ViewModel
            viewModel.sendOtp(phone)
            navController.navigate("auth/otp")
        }, modifier = Modifier.padding(top = 8.dp)) {
            Text("Send OTP")
        }

        Text("Or")

        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        Button(onClick = { viewModel.signInWithEmail(email, "") }, modifier = Modifier.padding(top = 8.dp)) {
            Text("Sign in with Email")
        }
    }
}
