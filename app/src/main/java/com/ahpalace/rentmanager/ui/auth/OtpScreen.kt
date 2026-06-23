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
fun OtpScreen(navController: NavController, viewModel: AuthViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    var otp by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Enter OTP")
        OutlinedTextField(value = otp, onValueChange = { otp = it }, label = { Text("OTP") })
        Button(onClick = {
            viewModel.verifyOtp(otp)
            navController.navigate("main/dashboard")
        }, modifier = Modifier.padding(top = 8.dp)) {
            Text("Verify")
        }
    }
}
